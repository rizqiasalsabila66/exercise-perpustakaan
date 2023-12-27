package com.winterhold.rest;

import com.winterhold.dto.category.UpsertCategoryDTO;
import com.winterhold.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/category")
public class CategoryRestController extends AbstractRestController {
    //    action method = endpoint
    @Autowired
    private CategoryService service;

    @GetMapping
    public ResponseEntity<Object> get(@RequestParam(defaultValue = "1") int page,
                                      @RequestParam(defaultValue = "") String name){
        var rows = service.getCategoryRows(name, page);
        return ResponseEntity.status(200).body(rows); //ini adalah respond body, di get tidak ada request body

    }

    @GetMapping("/{name}")
    public ResponseEntity<Object> get(@PathVariable(required = false) String name){
        var dto =service.findOne(name);
        return ResponseEntity.status(200).body(dto);
    }

    @PutMapping
    public ResponseEntity<Object> put(@Valid @RequestBody UpsertCategoryDTO dto, BindingResult bindingResult){
        if(!bindingResult.hasErrors()) {
            var response = service.save(dto);
            return ResponseEntity.status(200).body(response);
        }
        return ResponseEntity.status(422).body(getErrors(bindingResult.getAllErrors()));
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<Object> delete(@PathVariable(required = true) String name)//kalau parameternya 1 pakai path variable
    {
        var dependencies = service.countByCategoryName(name);
        if (dependencies == 0){
            service.delete(name);
            return ResponseEntity.status(200).body(name);
        }
        return ResponseEntity.status(409).body(dependencies);
    }

    @GetMapping("/books/{name}")
    public ResponseEntity<Object> getBooks(@RequestParam(defaultValue = "1") int page,
                                           @RequestParam(defaultValue = "") String name) {
        var rows = service.getBooks(name,page);
        return  ResponseEntity.status(200).body(rows);
    }
}
