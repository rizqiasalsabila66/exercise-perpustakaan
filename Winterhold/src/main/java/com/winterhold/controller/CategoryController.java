package com.winterhold.controller;

import com.winterhold.dto.book.UpsertBookDTO;
import com.winterhold.service.BookService;
import com.winterhold.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private BookService bookService;

    @GetMapping("/index")
    public String index(@RequestParam(defaultValue = "1") int page,
                        @RequestParam(defaultValue = "") String name,
                        Model model) {
        var rows = categoryService.getCategoryRows(name, page);
        var totalBook = categoryService.totalBook(name);
        model.addAttribute("grid", rows);
        model.addAttribute("totalPages", rows.getTotalPages());
        model.addAttribute("currentPage", page);
        model.addAttribute("name", name);
        model.addAttribute("totalBook", totalBook);
        return "category/category-index";
    }

    @GetMapping("/books")
    public String detail(@RequestParam(defaultValue = "1") Integer page,
                         @RequestParam(required = true)String name,
                         @RequestParam(required = false)String title,
                         @RequestParam(required = false)String author,
                         @RequestParam(required = false)Boolean isBorrowed,
                         Model model){
        var rows = categoryService.getBooks(name,title,author,isBorrowed,page);
        var header =categoryService.getHeaderInfo(name);
        model.addAttribute("grid",rows);
        model.addAttribute("totalPages",rows.getTotalPages());
        model.addAttribute("currentPage",page);
        model.addAttribute("header", header);
        model.addAttribute("name", name);
        model.addAttribute("title", title);
        model.addAttribute("author", author);
        model.addAttribute("isBorrowed", isBorrowed);
        return "category/category-books"; //return file htmlnya
    }

    @GetMapping("/insertBookForm")
    public String insertForm(Model model,@RequestParam(required = true) String name) {
        var dto = new UpsertBookDTO();
        dto.setCategoryName(name);
        dto.setIsBorrowed(false);
        var dropdownAuthor = bookService.getAuthorDropdown();
        model.addAttribute("dto", dto);
        model.addAttribute("authorDropdown", dropdownAuthor);
        model.addAttribute("categoryName",name);
        return "category/category-book-insert-form";
    }

    @PostMapping("/insertBook")
    public String insert(@Valid @ModelAttribute("dto") UpsertBookDTO dto, BindingResult bindingResult, Model model,
                         RedirectAttributes redirectAttrs, @RequestParam(required = true) String name) {
        if (bindingResult.hasErrors() == false) {
//            true kalau ada eror, false kalau salah
            bookService.save(dto);
            redirectAttrs.addAttribute("name", name);
            return "redirect:/category/books";
        }
        var dropdownAuthor = bookService.getAuthorDropdown();
        model.addAttribute("authorDropdown", dropdownAuthor);
        return "category/category-book-insert-form";
    }

    @GetMapping("/updateBookForm")
    public String updetForm(@RequestParam(required = true) String code,Model model,@RequestParam(required = true) String name) {
        var dto = new UpsertBookDTO();
        dto=bookService.findOne(code);
        dto.setCategoryName(name);
        var dropdownAuthor = bookService.getAuthorDropdown();
        model.addAttribute("dto", dto);
        model.addAttribute("authorDropdown", dropdownAuthor);
        model.addAttribute("categoryName",name);
        return "category/category-book-update-form";
    }

    @PostMapping("/updateBook")
    public String update(@Valid @ModelAttribute("dto") UpsertBookDTO dto, BindingResult bindingResult, Model model,
                         RedirectAttributes redirectAttrs,
                         @RequestParam(required = true) String name) {
        if (bindingResult.hasErrors() == false) {
//            true kalau ada eror, false kalau salah
            bookService.save(dto);
            redirectAttrs.addAttribute("name", name);
            return "redirect:/category/books";
        }
        var dropdownAuthor = bookService.getAuthorDropdown();
        model.addAttribute("authorDropdown", dropdownAuthor);
        return "category/category-book-update-form";
    }

    @GetMapping("/deleteBook")
    public String deleteBook(@RequestParam(required = true) String  code, Model model,
                             RedirectAttributes redirectAttrs,
                             @RequestParam(required = true) String name){
        var dependencies = bookService.countLoanByBook(code);
        if (dependencies == 0){
            bookService.delete(code);
            redirectAttrs.addAttribute("name", name);
            return "redirect:/category/books";
        }
        model.addAttribute("dependencies", dependencies);
        return "category/category-book-delete";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam(required = true) String  name, Model model){
        var dependencies = categoryService.countByCategoryName(name);
        if (dependencies == 0){
            categoryService.delete(name);
            return "redirect:/category/index";}
        model.addAttribute("dependencies", dependencies);
        return "category/category-delete";
    }


}
