package com.example.blogsample.service;

import com.example.blogsample.domain.Author;
import com.example.blogsample.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Author create(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public Author findAuthor(int id) {
        return authorRepository.getById(id);
    }

    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }
}
