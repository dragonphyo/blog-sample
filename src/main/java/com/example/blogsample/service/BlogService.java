package com.example.blogsample.service;

import com.example.blogsample.domain.Blog;

import java.util.List;

public interface BlogService {
    Blog create(Blog blog);
    Blog findById(int id);
    List<Blog> findAll();
    void deleteById(int id);
    void update(int id,Blog blog);
}
