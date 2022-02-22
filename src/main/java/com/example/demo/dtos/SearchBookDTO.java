package com.example.demo.dtos;

import com.fasterxml.jackson.annotation.JsonCreator;

public class SearchBookDTO {

    private final String searchValue;

    @JsonCreator
    public SearchBookDTO(String searchValue) {
        this.searchValue = searchValue;
    }

    public String getSearchValue() {
        return searchValue;
    }
}
