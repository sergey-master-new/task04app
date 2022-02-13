package com.example.task04app.controller;

import com.example.task04app.dto.BookDto;
import com.example.task04app.exception.marker.OnCreate;
import com.example.task04app.exception.marker.OnUpdate;
import com.example.task04app.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/book")
@Slf4j
@Validated
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> getBookDtoById(@PathVariable Long id) {
        log.info("Handling get book by id request: {}", id);
        BookDto bookDto = bookService.getBookDtoById(id);
        log.info("Response after handling get book: {}", bookDto);
        return new ResponseEntity<>(bookDto, HttpStatus.OK);
    }

    @GetMapping("/get-all")
    public List<BookDto> getAllBookDtos() {
        log.info("Handling get all books request");
        return bookService.getAllBookDtos();
    }

    @Validated(OnCreate.class)
    @PostMapping("/save")
    public BookDto saveBookDto(@Valid @RequestBody BookDto bookDto) throws IOException, SQLException {
        log.info("Request save: {}", bookDto);
        BookDto bookDtoSaved = bookService.saveBookDto(bookDto);
        log.info("Response save: {}", bookDtoSaved);
        return bookDtoSaved;
    }

    @Validated(OnUpdate.class)
    @PutMapping("/update")
    public ResponseEntity<BookDto> updateBookDto(@Valid @RequestBody BookDto bookDto) {
        log.info("Request update: {}", bookDto);
//        if (!bookService.containsBook(bookDto.getId())) {
//            log.info("Handling update response: the book was not found");
//            return new ResponseEntity<>(bookDto, HttpStatus.NOT_FOUND);
//        }
        BookDto bookDtoSaved = bookService.updateBookDto(bookDto);
        log.info("Response update: {}", bookDtoSaved);
        return new ResponseEntity<>(bookDtoSaved, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteBook(@RequestBody Long id) {
        log.info("Handling delete book request: {}", id);
//        if (id <= 0 | !bookService.containsBook(id)) {
//            log.info("Handling delete response: the book was not found");
//            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
//        }
        bookService.deleteBook(id);
        log.info("Handling delete response: completed successfully");
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
