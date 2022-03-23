/*
 * @author S.Maevsky
 * @since 03.03.2022, 16:59
 * @version V 1.0.0
 */

package com.example.task04app.persistance.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * The type Book.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Book {

    private Long id;

    private String isbn;

    private String name;

    private String author;

    private Integer page;

    private Integer weightInGrams;

    private Integer priceInKopecks;

}
