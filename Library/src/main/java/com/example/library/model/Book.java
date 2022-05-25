package com.example.library.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@AllArgsConstructor @Data
public class Book {
    @NotEmpty(message = "id is required")
    private String id;
    @NotEmpty(message = "name is required")
    private String name;
    @NotEmpty(message = "genre is required")
    private String genre;
    @NotNull(message = "price is required")
    private Integer price;
}
