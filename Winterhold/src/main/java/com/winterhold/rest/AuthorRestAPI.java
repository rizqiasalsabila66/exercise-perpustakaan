package com.winterhold.rest;

import com.winterhold.dto.author.UpsertAuthorDTO;
import com.winterhold.dto.customer.BiodataCustomerDTO;
import com.winterhold.service.AuthorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/author")
public class AuthorRestAPI extends AbstractRestController{

    //    action method = endpoint
    @Autowired
    private AuthorService service;

    @GetMapping
    public ResponseEntity<Object> get(@RequestParam(defaultValue = "1") int page,
                                      @RequestParam(defaultValue = "") String name){
        var rows = service.getAuthorRows(name,page);
        return ResponseEntity.status(200).body(rows); //ini adalah respond body, di get tidak ada request body

    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> get(@PathVariable(required = false) Long id){
        var dto =service.findOne(id);
        return ResponseEntity.status(200).body(dto);
    }

    @PatchMapping
    public ResponseEntity<Object> patch(@Valid @RequestBody UpsertAuthorDTO dto, BindingResult bindingResult){
        if(!bindingResult.hasErrors()) {
            var response = service.save(dto);
            return ResponseEntity.status(200).body(response);
        }
        return ResponseEntity.status(422).body(getErrors(bindingResult.getAllErrors()));
    }

    @PutMapping
    public ResponseEntity<Object> put(@Valid @RequestBody UpsertAuthorDTO dto, BindingResult bindingResult){
        if(!bindingResult.hasErrors()) {
            var response = service.save(dto);
            return ResponseEntity.status(200).body(response);
        }
        return ResponseEntity.status(422).body(getErrors(bindingResult.getAllErrors()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable(required = true) Long id)//kalau parameternya 1 pakai path variable
    {
        var dependencies = service.countByAuthorId(id);
        if (dependencies == 0){
            service.delete(id);
            return ResponseEntity.status(200).body(id);
        }
        return ResponseEntity.status(409).body("dependencies");
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<Object> getBooks(@RequestParam(defaultValue = "1") int page,
                                           @RequestParam(defaultValue = "") Long id) {
        var rows = service.getBookRowsByAuthor(id,page);
        return  ResponseEntity.status(200).body(rows);
    }
}
