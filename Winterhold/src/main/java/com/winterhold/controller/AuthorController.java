package com.winterhold.controller;

import com.winterhold.dto.author.UpsertAuthorDTO;
import com.winterhold.service.AuthorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/author")
public class AuthorController {
    @Autowired
    private AuthorService authorService;

    @GetMapping("/index")
    public String index(@RequestParam(defaultValue = "1") int page,
                        @RequestParam(defaultValue = "") String name,
                        Model model) {
        var rows = authorService.getAuthorRows(name, page);
        model.addAttribute("grid", rows);
        model.addAttribute("totalPages", rows.getTotalPages());
        model.addAttribute("currentPage", page);
        model.addAttribute("name", name);
        return "author/author-index";
    }

    @GetMapping("/upsertForm")
    public String upsertForm(@RequestParam(required = false) Long id, Model model){
        var dto = new UpsertAuthorDTO();
        if(id != null){
            dto =authorService.findOne(id);
        }
        model.addAttribute("dto", dto);
        return "author/author-form";
    }
    @PostMapping("/upsert")
    public String insert(@Valid @ModelAttribute("dto") UpsertAuthorDTO dto, BindingResult bindingResult){
        if(bindingResult.hasErrors() == false){
            authorService.save(dto);
            return "redirect:/author/index";
        }
        return "author/author-form";
    }
    @GetMapping("/books")
    public String detail(@RequestParam(defaultValue = "1") Integer page,
                         @RequestParam(required = true)Long id,
                         Model model){
        var rows = authorService.getBookRowsByAuthor(id,page);
        var header =authorService.getHeaderInfo(id);
        model.addAttribute("grid",rows);
        model.addAttribute("totalPages",rows.getTotalPages());
        model.addAttribute("currentPage",page);
        model.addAttribute("header", header);
        return "author/author-books";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam(required = true) Long id, Model model){
        var dependencies = authorService.countByAuthorId(id);
        if (dependencies == 0){
            authorService.delete(id);
            return "redirect:/author/index";}
        model.addAttribute("dependencies", dependencies);
        return "author/author-delete";
    }

}
