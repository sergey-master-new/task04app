package com.example.task04app.external.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
public class BookOpenLibraryDtos {

    List<BookOpenLibraryDto> docs;

    public BookOpenLibraryDtos() {

        docs = new ArrayList<>();
    }

}
