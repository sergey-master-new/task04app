package com.example.task04app.external.pojo;

import com.example.task04app.external.dto.BookOpenLibraryDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
public class ResponseBookOpenLibrary {

    @JsonProperty(value = "docs")
    private List<BookOpenLibraryDto> bookOpenLibraryDtoList;

    public ResponseBookOpenLibrary() {

        bookOpenLibraryDtoList = new ArrayList<>();
    }

}
