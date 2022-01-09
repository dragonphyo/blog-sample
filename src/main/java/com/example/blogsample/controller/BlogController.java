package com.example.blogsample.controller;

import com.example.blogsample.domain.Blog;
import com.example.blogsample.service.AuthorService;
import com.example.blogsample.service.BlogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
public class BlogController {

    private final AuthorService authorService;
    private final BlogService blogService;

    public BlogController(AuthorService authorService, BlogService blogService) {
        this.authorService = authorService;
        this.blogService = blogService;
    }

    @GetMapping("/blog")
    public String create(Model model) {
        model.addAttribute("blog", new Blog());
        model.addAttribute("authors", authorService.findAll());
        return "admin/blogForm";
    }

    @PostMapping("/blog")
    public String process(@Valid Blog blog, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            return "admin/blogForm";
        }
        blogService.create(blog);
        attributes.addFlashAttribute("success", true);
        return "redirect:/blogs";
    }

    @GetMapping("/blogs")
    public String showAllBlogs(Model model) {
        model.addAttribute("blogs", blogService.findAll());
        model.addAttribute("success",model.containsAttribute("success"));
        return "admin/blogs";
    }

    @ModelAttribute(name = "categories")
    public List<String> categoryList() {
        return List.of("Horror", "Comedy", "Thriller", "Tragedy", "Romance", "Adventure", "Accounting", "Action", "Adult", "Animal", "Drama");
    }
}
