/*
 * @author S.Maevsky
 * @since 04.03.2022, 16:12
 * @version V 1.0.0
 */

package com.example.task04app.external.openlibrary.pojo;

import com.example.task04app.external.openlibrary.dto.BookOpenLibraryDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


/**
 * The type Response book open library.
 */
@Data
@AllArgsConstructor
public class ResponseBookOpenLibrary {

    @JsonProperty(value = "docs")
    private List<BookOpenLibraryDto> bookOpenLibraryDtoList;

    /**
     * Instantiates a new Response book open library.
     */
    public ResponseBookOpenLibrary() {

        bookOpenLibraryDtoList = new ArrayList<>();
    }

}
