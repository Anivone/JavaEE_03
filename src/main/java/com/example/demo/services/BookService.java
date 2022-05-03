package com.example.demo.services;

import com.example.demo.models.Book;
import com.example.demo.repositories.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service()
@Transactional
@RequiredArgsConstructor
public class BookService {
    private final EntityManager entityManager;

    @Autowired
    private final BookRepository bookRepository;

    public Book createBook(Book book) {
        return entityManager.merge(book);
    }

    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    public List<Book> filterBooks(String filter) {
        return bookRepository.findByIsbnContainsOrAuthorContainsOrTitleContains(filter, filter, filter);
    }

    public Book getBookById(int id) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isEmpty()) return null;

        return book.get();
    }

    public Book getBookByIsbnAndAuthor(String isbn, String author) {
        Optional<Book> book = bookRepository.findByIsbnAndAuthor(isbn, author);
        if (book.isEmpty()) return null;

        return book.get();
    }

}
