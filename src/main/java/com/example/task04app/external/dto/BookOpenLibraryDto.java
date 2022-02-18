package com.example.task04app.external.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookOpenLibraryDto {

    private ArrayList<String> isbn;

    private String title;

    private ArrayList<String> author_name;

    private Integer number_of_pages_median;

}
