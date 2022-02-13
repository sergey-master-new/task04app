package com.example.task04app.external.dto;

import com.example.task04app.dto.BookDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class BookDbAndOpenLibraryDtos {

    List<BookDto> bookDtoList;

    List<BookOpenLibraryDto> bookOpenLibraryDtoList;

    public BookDbAndOpenLibraryDtos() {

        bookDtoList = new ArrayList<>();
        bookOpenLibraryDtoList = new ArrayList<>();
    }
}
