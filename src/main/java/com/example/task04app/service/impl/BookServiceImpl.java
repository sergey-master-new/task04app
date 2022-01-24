package com.example.task04app.service.impl;

import com.example.task04app.dto.BookDto;
import com.example.task04app.exception.NotFoundException;
import com.example.task04app.mapper.BookMapper;
import com.example.task04app.model.Book;
import com.example.task04app.persistance.dao.BookDao;
import com.example.task04app.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {

    private final BookDao bookJdbcDao;

    private final BookMapper mapper;

    @Override
    public BookDto getBookDtoById(Long id) {

        Book book = bookJdbcDao.getById(id);
        checkOnNull(id, book);

        return mapper.toBookDto(book);
    }

    @Override
    public List<BookDto> getAllBookDtos() {

        List<Book> bookList = bookJdbcDao.getAll();

        return mapper.mapToBookDtoList(bookList);
    }

    @Override
    public BookDto saveBookDto(BookDto bookDto) {

        Book book = mapper.toBook(bookDto);
        Book bookSaved = bookJdbcDao.save(book);

        return mapper.toBookDto(bookSaved);
    }

    @Override
    public BookDto updateBookDto(BookDto bookDto) {

        Book book = mapper.toBook(bookDto);
        Book bookUpdated = bookJdbcDao.update(book);
        checkOnNull(bookDto.getId(), bookUpdated);

        return mapper.toBookDto(bookUpdated);
    }

    @Override
    public void deleteBook(Long id) {

        bookJdbcDao.delete(id);
    }

    private void checkOnNull(Long id, Book book) {

        if (book == null) {
            log.info("Book was not found by id: {}", id);
            throw new NotFoundException("This book was not found by id: " + id);
        }
    }
}
