package com.example.blogsample.repository;

import com.example.blogsample.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository  extends JpaRepository<Author,Integer> {
}
