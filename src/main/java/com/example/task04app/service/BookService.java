package com.example.task04app.service;

import com.example.task04app.dto.BookDto;

import java.util.List;

public interface BookService {

    BookDto getBookDtoById(Long id);

    List<BookDto> getAllBookDtos();

    BookDto saveBookDto(BookDto bookDto);

    BookDto updateBookDto(BookDto bookDto);

    void deleteBook(Long id);
}
