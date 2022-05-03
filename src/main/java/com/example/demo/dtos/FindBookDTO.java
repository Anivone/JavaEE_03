package com.example.demo.dtos;

import com.fasterxml.jackson.annotation.JsonCreator;

public class FindBookDTO {

    private final String isbn;
    private final String author;

    @JsonCreator
    public FindBookDTO(String isbn, String author) {
        this.isbn = isbn;
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getAuthor() {
        return author;
    }
}
