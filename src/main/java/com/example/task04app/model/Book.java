package com.example.task04app.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Book {

    private Long id;

    private String isbn;

    private String name;

    private String author;

    private Integer pageCount;

    private Integer weightInGrams;

    private Integer priceInKopecks;
}
