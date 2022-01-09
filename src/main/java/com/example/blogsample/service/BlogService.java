package com.example.blogsample.service;

import com.example.blogsample.domain.Blog;

import java.util.List;

public interface BlogService {
    Blog create(Blog blog);
    Blog findBlog(int id);
    List<Blog> findAll();
}
