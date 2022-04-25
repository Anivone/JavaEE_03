package com.example.demo.services;

import com.example.demo.models.Book;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class BookServiceTest {

    @Autowired
    private BookService bookService;

    @Test
    void createBook() {
        Book book = bookService.createBook(
                Book.builder().isbn("isbn1").author("author1").title("title1")
                        .build());
        Book foundBook = bookService.getBookById(book.getId());
        assertBooks(book, foundBook);
    }

    @Test
    void findById() {
        Book book = bookService.createBook(
                Book.builder().isbn("isbn1").author("author1").title("title1")
                        .build());
        Book book2 = bookService.createBook(
                Book.builder().isbn("isbn2").author("author2").title("title2")
                        .build());
        Book book3 = bookService.createBook(
                Book.builder().isbn("isbn3").author("author3").title("title3")
                        .build());
        Book book4 = bookService.createBook(
                Book.builder().isbn("isbn4").author("author4").title("title4")
                        .build());

        assertBooks(book, bookService.getBookById(book.getId()));
        assertBooks(book2, bookService.getBookById(book2.getId()));
        assertBooks(book3, bookService.getBookById(book3.getId()));
        assertBooks(book4, bookService.getBookById(book4.getId()));
    }

    @Test
    void filter() {
        Book book = bookService.createBook(
                Book.builder().isbn("isbn1").author("author").title("title1")
                        .build());
        Book book2 = bookService.createBook(
                Book.builder().isbn("isbn2").author("author2").title("title1")
                        .build());
        Book book3 = bookService.createBook(
                Book.builder().isbn("isbn3").author("author").title("title3")
                        .build());
        Book book4 = bookService.createBook(
                Book.builder().isbn("isbn4").author("author2").title("title1")
                        .build());

        List<Book> filter = bookService.filterBooks("author2");

        assertBooks(filter.get(0), book2);
        assertBooks(filter.get(1), book4);
    }

    void assertBooks(Book book1, Book book2) {
        Assertions.assertEquals(book1.getId(), book2.getId());
        Assertions.assertEquals(book1.getIsbn(), book2.getIsbn());
        Assertions.assertEquals(book1.getAuthor(), book2.getAuthor());
        Assertions.assertEquals(book1.getTitle(), book2.getTitle());
    }

}
