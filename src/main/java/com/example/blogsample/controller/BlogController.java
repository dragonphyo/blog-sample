package com.example.blogsample.controller;

import com.example.blogsample.domain.Blog;
import com.example.blogsample.service.AuthorService;
import com.example.blogsample.service.BlogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
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
    public String process(@Valid Blog blog, BindingResult result, RedirectAttributes attributes , Model model) {
        if (result.hasErrors()) {
            model.addAttribute("authors",authorService.findAll());
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
        model.addAttribute("delete",model.containsAttribute("delete"));
        model.addAttribute("update",model.containsAttribute("update"));
        return "admin/blogs";
    }

    @ModelAttribute(name = "categories")
    public List<String> categoryList() {
        return List.of("Horror", "Comedy", "Thriller", "Tragedy", "Romance", "Adventure", "Accounting", "Action", "Adult", "Animal", "Drama");
    }


    @GetMapping("/blog/{id}")
    public String showBlogDetails(Model model,@PathVariable("id") int id){
        model.addAttribute("blog",blogService.findById(id));
        return "admin/blog";
    }

    @GetMapping("/blog/delete/{id}")
    public String delete(Model model,@PathVariable int id,RedirectAttributes redirectAttributes){
        blogService.deleteById(id);
        redirectAttributes.addFlashAttribute("delete",true);
        return "redirect:/blogs";
    }

    int bid;
    @GetMapping("/blog/update/{id}")
    public String updateBlog(@PathVariable("id") int id,Model model){
        model.addAttribute("blog",blogService.findById(id));
        model.addAttribute("authors",authorService.findAll());
        this.bid = id;
        return "admin/updateForm";
    }

    @PostMapping("/blog/update")
    public String updateBlog(Blog blog,RedirectAttributes redirectAttributes){
            blogService.update(bid,blog);
            redirectAttributes.addFlashAttribute("update",true);
            return "redirect:/blogs";
    }
}
