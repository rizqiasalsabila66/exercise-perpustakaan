package com.winterhold.controller;

import com.winterhold.dto.customer.UpsertCustomerDTO;
import com.winterhold.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService service;

    @GetMapping("/index")
    public String index(@RequestParam(defaultValue = "1") int page,
                        @RequestParam(defaultValue = "") String membershipNumber,
                        @RequestParam(defaultValue = "") String name,
                        Model model) {
        var rows = service.getCustomerRows(membershipNumber,name, page);
        model.addAttribute("grid", rows);
        model.addAttribute("totalPages", rows.getTotalPages());
        model.addAttribute("currentPage", page);
        model.addAttribute("name", name);
        model.addAttribute("membershipNumber", membershipNumber);
        return "customer/customer-index";
    }
    @GetMapping("/insertForm")
    public String insertForm(Model model){
        var dto = new UpsertCustomerDTO();
        model.addAttribute("dto", dto);
        return "customer/customer-insert-form";
    }

    @PostMapping("/insert")
    public String insert(@Valid @ModelAttribute("dto") UpsertCustomerDTO dto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors() == false) {
            LocalDate register = LocalDate.now().plusYears(2);
            dto.setMembershipExpireDate(register);
            service.save(dto);
            return "redirect:/customer/index";
        }
        return "customer/customer-insert-form";
    }

    @GetMapping("/updateForm")
    public String updetForm(@RequestParam(required = true) String membershipNumber,Model model) {
        var dto = new UpsertCustomerDTO();
        dto=service.findOne(membershipNumber);
        model.addAttribute("dto", dto);
        return "customer/customer-update-form";
    }

    @PostMapping("/update")
    public String update(@Valid @ModelAttribute("dto") UpsertCustomerDTO dto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors() == false) {
            service.save(dto);
            return "redirect:/customer/index";
        }
        return "customer/customer-update-form";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam(required = true) String  membershipNumber,
                         Model model){
        var dependencies = service.countLoanByCustomer(membershipNumber);
        if (dependencies == 0){
            service.delete(membershipNumber);
            return "redirect:/customer/index";
        }
        model.addAttribute("dependencies", dependencies);
        return "customer/customer-delete";
    }

    @GetMapping("/extend")
    public String extend(@RequestParam(required = true) String  membershipNumber,
                         Model model){
        service.extend(membershipNumber);
        return "redirect:/customer/index";
    }

}
