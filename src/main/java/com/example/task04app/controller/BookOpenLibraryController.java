package com.example.task04app.controller;


import com.example.task04app.external.OpenLibraryExchangeClient;
import com.example.task04app.external.dto.BookDbAndOpenLibraryDtos;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/open-library")
public class BookOpenLibraryController {

    private final OpenLibraryExchangeClient openLibraryExchangeClient;

    @GetMapping
    public ResponseEntity<BookDbAndOpenLibraryDtos> getBookDtosByAuthor(
            @RequestParam(value = "author", required = false) String author) {

        log.info("Handling get books from open library and DB by author request: {}", author);
        BookDbAndOpenLibraryDtos responseBookDtoList = openLibraryExchangeClient.getBookDtosByAuthor(author);
        log.info("Response after get books from open library and DB by author request: {}", responseBookDtoList);

        return new ResponseEntity<>(responseBookDtoList, HttpStatus.OK);
    }
}
