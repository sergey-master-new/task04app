package com.example.task04app.controller;

import com.example.task04app.dto.BookDto;
import com.example.task04app.exception.marker.OnCreate;
import com.example.task04app.exception.marker.OnUpdate;
import com.example.task04app.service.BookService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


@Slf4j
@Validated
@AllArgsConstructor
@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> getBookDtoById(@PathVariable Long id) {

        log.info("Handling get book by id request: {}", id);
        BookDto bookDto = bookService.getBookDtoById(id);
        log.info("Response after handling get book: {}", bookDto);

        return new ResponseEntity<>(bookDto, HttpStatus.OK);
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<BookDto>> getAllBookDtos() {

        log.info("Handling get all books request");
        List<BookDto> bookDtoList = bookService.getAllBookDtos();

        return new ResponseEntity<>(bookDtoList, HttpStatus.OK);
    }

    @Validated(OnCreate.class)
    @PostMapping("/save")
    public ResponseEntity<BookDto> saveBookDto(@Valid @RequestBody BookDto bookDto) throws IOException, SQLException {

        log.info("Request save: {}", bookDto);
        BookDto bookDtoSaved = bookService.saveBookDto(bookDto);
        log.info("Response save: {}", bookDtoSaved);

        return new ResponseEntity<>(bookDtoSaved, HttpStatus.CREATED);
    }

    @Validated(OnUpdate.class)
    @PutMapping("/update")
    public ResponseEntity<BookDto> updateBookDto(@Valid @RequestBody BookDto bookDto) {

        log.info("Request update: {}", bookDto);
        BookDto bookDtoUpdated = bookService.updateBookDto(bookDto);
        log.info("Response update: {}", bookDtoUpdated);

        return new ResponseEntity<>(bookDtoUpdated, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteBook(@RequestBody Long id) {

        log.info("Handling delete book request: {}", id);
        bookService.deleteBook(id);
        log.info("Handling delete response: completed successfully");

        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @GetMapping("/get")
    public ResponseEntity<List<BookDto>> getBookDtosByAuthor(
            @RequestParam(value = "author", required = false) String author) {

        log.info("Handling get books by author request: {}", author);
        List<BookDto> bookDtoList = bookService.getBookDtosByAuthor(author);

        return new ResponseEntity<>(bookDtoList, HttpStatus.OK);
    }

}
