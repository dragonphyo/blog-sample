package com.example.blogsample.service;

import com.example.blogsample.domain.Blog;
import com.example.blogsample.repository.BlogRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {
    private final BlogRepository blogRepository;

    public BlogServiceImpl(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    @Override
    public Blog create(Blog blog) {
        return blogRepository.save(blog);
    }

    @Override
    public Blog findById(int id) {
        return blogRepository.findById(id).get();
    }

    @Override
    public List<Blog> findAll() {
        return blogRepository.findAll();
    }

    @Override
    public void deleteById(int id) {
        blogRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void update(int id, Blog blog) {
         Blog oldBlog = blogRepository.findById(id).get();
         oldBlog.setAuthor(blog.getAuthor());
         oldBlog.setBody(blog.getBody());
         oldBlog.setTitle(blog.getTitle());
         oldBlog.setCategory(blog.getCategory());
    }
}
