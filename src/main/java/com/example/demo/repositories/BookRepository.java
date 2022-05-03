package com.example.demo.repositories;

import com.example.demo.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findByIsbnContainsOrAuthorContainsOrTitleContains(String isbn, String author, String title);

    Optional<Book> findByIsbnAndAuthor(String isbn, String author);
}
