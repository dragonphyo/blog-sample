package com.example.blogsample.controller;


import com.example.blogsample.domain.Author;
import com.example.blogsample.service.AuthorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class AuthorController {


    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }


    @GetMapping("/author")
    public String create(Model model) {
        model.addAttribute("author", new Author());
        return "admin/authorForm";
    }

    @PostMapping("/author")
    public String process(@Valid Author author, BindingResult result) {
        if(result.hasErrors()){
            return "admin/authorForm";
        }
        authorService.create(author);
        return "redirect:/authors";
    }

    @GetMapping("/authors")
    public String showAuthors(Model model){

        model.addAttribute("authors",authorService.findAll());
        return "admin/authors";
    }

    @GetMapping("/layout")
    public String testLayout(){
        return "layout/adminlayout";
    }
}
