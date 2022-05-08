package com.example.demo;

import com.example.demo.models.Book;
import com.example.demo.repositories.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class BookValidator {
    private final BookRepository bookRepository;

    public boolean isNewBookValid(final Book book) {

        if (book.getIsbn().isEmpty() ||
                book.getAuthor().isEmpty() ||
                book.getTitle().isEmpty()) {
            throw new IllegalArgumentException();
        }

        if (bookRepository.findByIsbnAndAuthor(book.getIsbn(), book.getAuthor()).isEmpty()) {
            throw new IllegalArgumentException("Book with such ISBN already exists");
        }

        return true;
    }

}
