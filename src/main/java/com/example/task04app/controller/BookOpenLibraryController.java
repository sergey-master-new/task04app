/*
 * @author S.Maevsky
 * @since 03.03.2022, 16:54
 * @version V 1.0.0
 */

package com.example.task04app.controller;

import com.example.task04app.external.openlibrary.dto.BookDbAndOpenLibraryDtoList;
import com.example.task04app.service.BookOpenLibraryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * The type Book open library controller.
 */
@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/openlibrary")
public class BookOpenLibraryController {

    private final BookOpenLibraryService bookOpenLibraryService;

    /**
     * Gets book dtos by author from database and openlibrary.org.
     *
     * @param author the author
     * @return the response entity with list of book dto from database
     * and list of book dto from openlibrary.org, HttpStatus.OK
     */
    @GetMapping
    public ResponseEntity<BookDbAndOpenLibraryDtoList> getBookDtosByAuthor(
            @RequestParam(value = "author", required = false) String author) {

        log.debug("Handling get books from openlibrary and DB by author: {}", author);
        BookDbAndOpenLibraryDtoList responseBookDtoList = bookOpenLibraryService.getBookDtosByAuthor(author);
        log.debug("Response after get books from openlibrary and DB by author: {}", responseBookDtoList);

        return new ResponseEntity<>(responseBookDtoList, HttpStatus.OK);
    }
}
