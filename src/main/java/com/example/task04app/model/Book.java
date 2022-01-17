package com.example.task04app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    private Long id;
    private String isbn;
    private String name;
    private Author authors;
    private Integer pageCount;
    private Integer weightInGrams;
    private Integer priceInKopecks;

}


