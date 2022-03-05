/*
 * @author S.Maevsky
 * @since 04.03.2022, 16:08
 * @version V 1.0.0
 */

package com.example.task04app.external.openlibrary.dto;

import com.example.task04app.dto.BookDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Book db and open library dto list.
 */
@Data
@AllArgsConstructor
public class BookDbAndOpenLibraryDtoList {

    private List<BookDto> bookDtoList;

    private List<BookOpenLibraryDto> bookOpenLibraryDtoList;

    /**
     * Instantiates a new Book db and open library dto list.
     */
    public BookDbAndOpenLibraryDtoList() {

        bookDtoList = new ArrayList<>();
        bookOpenLibraryDtoList = new ArrayList<>();
    }
}
