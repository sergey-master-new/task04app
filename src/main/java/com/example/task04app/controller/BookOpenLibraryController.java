package com.example.task04app.controller;

import com.example.task04app.external.dto.BookDbAndOpenLibraryDtoList;
import com.example.task04app.service.BookOpenLibraryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/open-library")
public class BookOpenLibraryController {

    private final BookOpenLibraryService bookOpenLibraryService;

    @GetMapping
    public ResponseEntity<BookDbAndOpenLibraryDtoList> getBookDtosByAuthor(
            @RequestParam(value = "author", required = false) String author) {

        log.info("Handling get books from open library and DB by author: {}", author);
        BookDbAndOpenLibraryDtoList responseBookDtoList = bookOpenLibraryService.getBookDtosByAuthor(author);
        log.info("Response after get books from open library and DB by author: {}", responseBookDtoList);

        return new ResponseEntity<>(responseBookDtoList, HttpStatus.OK);
    }
}
