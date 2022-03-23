/*
 * @author S.Maevsky
 * @since 03.03.2022, 16:38
 * @version V 1.0.0
 */

package com.example.task04app.controller;

import com.example.task04app.dto.BookDto;
import com.example.task04app.exception.marker.OnCreate;
import com.example.task04app.exception.marker.OnUpdate;
import com.example.task04app.external.alfabank.dto.BookPriceInCurrenciesDto;
import com.example.task04app.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


/**
 * The type Book controller.
 */
@Slf4j
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    /**
     * Gets book dto by id.
     *
     * @param id the id
     * @return BookDto
     */
    @GetMapping("/{id}")
    public ResponseEntity<BookDto> getBookDtoById(@PathVariable Long id) {

        log.debug("Handling get book by id request: {}", id);
        BookDto bookDto = bookService.getBookDtoById(id);
        log.debug("Response after handling get book: {}", bookDto);

        return new ResponseEntity<>(bookDto, HttpStatus.OK);
    }

    /**
     * Gets all book dtos.
     *
     * @return the response entity with list of book dto, HttpStatus.OK
     */
    @GetMapping("/get-all")
    public ResponseEntity<List<BookDto>> getAllBookDtos() {

        log.debug("Handling get all books request");
        List<BookDto> bookDtoList = bookService.getAllBookDtos();
        log.debug("Response after handling get all books: {}", bookDtoList);

        return new ResponseEntity<>(bookDtoList, HttpStatus.OK);
    }

    /**
     * Save book dto response entity.
     *
     * @param bookDto the book dto
     * @return the response entity with book dto saved, HttpStatus.CREATED
     */
    @Validated(OnCreate.class)
    @PostMapping("/save")
    public ResponseEntity<BookDto> saveBookDto(@Valid @RequestBody BookDto bookDto) {

        log.debug("Request save: {}", bookDto);
        BookDto bookDtoSaved = bookService.saveBookDto(bookDto);
        log.debug("Response save: {}", bookDtoSaved);

        return new ResponseEntity<>(bookDtoSaved, HttpStatus.CREATED);
    }

    /**
     * Update book dto response entity.
     *
     * @param bookDto the book dto
     * @return the response entity with book dto updated, HttpStatus.OK
     */
    @Validated(OnUpdate.class)
    @PutMapping("/update")
    public ResponseEntity<BookDto> updateBookDto(@Valid @RequestBody BookDto bookDto) {

        log.debug("Request update: {}", bookDto);
        BookDto bookDtoUpdated = bookService.updateBookDto(bookDto);
        log.debug("Response update: {}", bookDtoUpdated);

        return new ResponseEntity<>(bookDtoUpdated, HttpStatus.OK);
    }

    /**
     * Delete book response entity.
     *
     * @param id the identification number of book.
     * @return the response entity with HttpStatus.OK
     */
    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteBook(@RequestBody Long id) {

        log.debug("Handling delete book request: {}", id);
        bookService.deleteBook(id);
        log.debug("Handling delete response: completed successfully");

        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    /**
     * Gets book dtos by author.
     *
     * @param author the author
     * @return the response entity with list of book dto, HttpStatus.OK
     */
    @GetMapping("/get")
    public ResponseEntity<List<BookDto>> getBookDtosByAuthor(
            @RequestParam(value = "author", required = false) String author) {

        log.debug("Handling get books by author request: {}", author);
        List<BookDto> bookDtoList = bookService.getBookDtosByAuthor(author);
        log.debug("Handling get books by author request: {}, completed successfully", author);

        return new ResponseEntity<>(bookDtoList, HttpStatus.OK);
    }

    /**
     * Gets book dtos by title.
     *
     * @param title the title
     * @return the response entity with list of book dto, HttpStatus.OK
     */
    @GetMapping("/price/{title}")
    public ResponseEntity<List<BookPriceInCurrenciesDto>> getBookDtosByTitle(@PathVariable String title) {

        log.debug("Handling get books by title request: {}", title);
        List<BookPriceInCurrenciesDto> bookDtoList = bookService.getBookDtosInCurrenciesByTitle(title);
        log.debug("Handling get books by title with prices in different currencies response: {}", bookDtoList);

        return new ResponseEntity<>(bookDtoList, HttpStatus.OK);
    }

}
