package com.winterhold.rest;

import com.winterhold.dto.customer.BiodataCustomerDTO;
import com.winterhold.dto.customer.UpsertCustomerDTO;
import com.winterhold.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customer")
public class CustomerRestController extends AbstractRestController{
    @Autowired
    private CustomerService service;

    @GetMapping
    public ResponseEntity<Object> get(@RequestParam(defaultValue = "1") int page,
                                      @RequestParam(defaultValue = "") String membershipNumber,
                                      @RequestParam(defaultValue = "") String name){
        var rows = service.getCustomerRows(membershipNumber,name, page);
        return ResponseEntity.status(200).body(rows); //ini adalah respond body, di get tidak ada request body

    }

    @GetMapping("/{membershipNumber}")
    public ResponseEntity<Object> get(@PathVariable(required = false) String membershipNumber){
        var dto =service.findOne(membershipNumber);
        return ResponseEntity.status(200).body(dto);
    }

    @PutMapping
    public ResponseEntity<Object> put(@Valid @RequestBody UpsertCustomerDTO dto, BindingResult bindingResult){
        if(!bindingResult.hasErrors()) {
            var response = service.save(dto);
            return ResponseEntity.status(200).body(response);
        }
        return ResponseEntity.status(422).body(getErrors(bindingResult.getAllErrors()));
    }

    @GetMapping("/biodata/{membershipNumber}")
    public ResponseEntity<Object> getBiodata(@PathVariable(required = false) String membershipNumber){
        var dto = new BiodataCustomerDTO();
        dto= service.findOneContact(membershipNumber);
        return  ResponseEntity.status(200).body(dto);
    }


    @DeleteMapping("/{membershipNumber}")
    public ResponseEntity<Object> delete(@PathVariable(required = true) String membershipNumber)//kalau parameternya 1 pakai path variable
    {
        var dependencies = service.countLoanByCustomer(membershipNumber);
        if (dependencies == 0){
            service.delete(membershipNumber);
            return ResponseEntity.status(200).body(membershipNumber);
        }
        return ResponseEntity.status(409).body(dependencies);
    }

    @PatchMapping("/{membershipNumber}")
    public ResponseEntity<Object> extend(@PathVariable(required = false) String membershipNumber){
        var response = service.extend(membershipNumber);
        return  ResponseEntity.status(200).body(response);
    }

}
