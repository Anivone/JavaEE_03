package com.example.demo;

import com.example.demo.dtos.SearchBookDTO;
import com.example.demo.models.Book;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BookController {

    List<Book> books = new ArrayList<>();

    @RequestMapping(value = "/books", method = RequestMethod.POST)
    public ResponseEntity<Book> addNewBook(@RequestBody Book book) {
        Book newBook = new Book(book.title, book.isbn, book.author);
        books.add(book);
        return ResponseEntity.ok(book);
    }

    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public ResponseEntity<List<Book>> getBooks() {
        return ResponseEntity.ok(books);
    }

    @RequestMapping(value = "/books/search", method = RequestMethod.POST)
    public ResponseEntity<List<Book>> searchBook(
            @RequestBody final SearchBookDTO searchBook
    ) {
        List<Book> book = books.stream()
                .filter(element -> element.title.contains(searchBook.getSearchValue()) ||
                        element.isbn.contains(searchBook.getSearchValue()))
                .toList();

        return ResponseEntity.ok(book);
    }

}
