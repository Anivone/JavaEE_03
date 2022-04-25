package com.example.demo;

import com.example.demo.dtos.SearchBookDTO;
import com.example.demo.models.Book;
import com.example.demo.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping(value = "/books", method = RequestMethod.POST)
    public ResponseEntity<Book> addNewBook(@RequestBody Book book) {
        Book newBook = bookService.createBook(book);
        return ResponseEntity.ok(newBook);
    }

    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public ResponseEntity<Book[]> getBooks() {
        List<Book> books = bookService.getBooks();
        return ResponseEntity.ok(books.toArray(new Book[0]));
    }

    @RequestMapping(value = "/books/search", method = RequestMethod.POST)
    public ResponseEntity<Book[]> searchBook(
            @RequestBody final SearchBookDTO searchBook
    ) {
        List<Book> books = bookService.filterBooks(searchBook.getSearchValue());

        return ResponseEntity.ok(books.toArray(new Book[0]));
    }

}
