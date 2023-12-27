package com.winterhold.rest;

import com.winterhold.dto.book.UpsertBookDTO;
import com.winterhold.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/book")
public class BookRestController extends AbstractRestController{

    //    action method = endpoint
    @Autowired
    private BookService service;

    @GetMapping
    public ResponseEntity<Object> get(@RequestParam(defaultValue = "1") int page){
        var rows = service.getBookRows(page);
        return ResponseEntity.status(200).body(rows); //ini adalah respond body, di get tidak ada request body

    }

    @GetMapping("/{code}")
    public ResponseEntity<Object> get(@PathVariable(required = false) String code){
        var dto =service.findOne(code);
        return ResponseEntity.status(200).body(dto);
    }

    @PutMapping
    public ResponseEntity<Object> put(@Valid @RequestBody UpsertBookDTO dto, BindingResult bindingResult){
        if(!bindingResult.hasErrors()) {
            var response = service.save(dto);
            return ResponseEntity.status(200).body(response);
        }
        return ResponseEntity.status(422).body(getErrors(bindingResult.getAllErrors()));
    }

    @DeleteMapping("/{code}")
    public ResponseEntity<Object> delete(@PathVariable(required = true) String code)//kalau parameternya 1 pakai path variable
    {
        var dependencies = service.countLoanByBook(code);
        if (dependencies == 0){
            service.delete(code);
            return ResponseEntity.status(200).body(code);
        }
        return ResponseEntity.status(409).body("dependencies");
    }

    @GetMapping("/summary/{code}")
    public ResponseEntity<Object> getSummary(@PathVariable(required = false) String code){
        var summary = service.getSummary(code);
        if(summary.equals("")){
            return ResponseEntity.status(200).body("Tidak ada summary");
        }
        return ResponseEntity.status(200).body(summary);
    }

}
