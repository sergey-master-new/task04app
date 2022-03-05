/*
 * @author S.Maevsky
 * @since 04.03.2022, 16:11
 * @version V 1.0.0
 */

package com.example.task04app.external.openlibrary.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;


/**
 * The type Book open library dto.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookOpenLibraryDto {

    private ArrayList<String> isbn;

    private String title;

    @JsonProperty("author_name")
    private ArrayList<String> authorName;

    @JsonProperty("number_of_pages_median")
    private Integer numberOfPagesMedian;

}
