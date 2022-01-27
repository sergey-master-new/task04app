package com.example.task04app.service.impl;

import com.example.task04app.dto.BookDto;
import com.example.task04app.exception.NotFoundException;
import com.example.task04app.mapper.dto.BookMapper;
import com.example.task04app.model.Book;
import com.example.task04app.persistance.dao.BookDao;
import com.example.task04app.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class BookServiceImpl implements BookService {

    private final BookDao bookDao;
    private final BookMapper mapper;

    @Autowired
    public BookServiceImpl(@Qualifier("bookJdbcTemplateDaoImpl") BookDao bookDao,
                           BookMapper mapper) {
        this.bookDao = bookDao;
        this.mapper = mapper;
    }


    @Override
    public BookDto getBookDtoById(Long id) {

        Book book = null;

        try {
            book = bookDao.getById(id);
            checkOnNull(id, book);
        } catch (EmptyResultDataAccessException ex) {

            log.info(String.format("Exception when handling get book." +
                    " Book by id= %n was not found", id), ex.getMessage());
            throw new NotFoundException(String.format("Book by id= %d was not found", id));
        }

        return mapper.toBookDto(book);
    }

    @Override
    public List<BookDto> getAllBookDtos() {

        List<Book> bookList = bookDao.getAll();

        return mapper.mapToBookDtoList(bookList);
    }

    @Override
    public BookDto saveBookDto(BookDto bookDto) {

        Book book = mapper.toBook(bookDto);
        Book bookSaved = bookDao.save(book);

        return mapper.toBookDto(bookSaved);
    }

    @Override
    public BookDto updateBookDto(BookDto bookDto) {

        Book book = mapper.toBook(bookDto);
        Book bookUpdated = null;

        try {
            bookUpdated = bookDao.update(book);
            checkOnNull(bookDto.getId(), bookUpdated);
        } catch (EmptyResultDataAccessException ex) {

            log.info(String.format("Exception when handling get book." +
                    " Book by id= %n was not found", bookDto.getId()), ex.getMessage());
            throw new NotFoundException(
                    String.format("Book by id= %d was not found", bookDto.getId()));
        }

        return mapper.toBookDto(bookUpdated);
    }

    @Override
    public void deleteBook(Long id) {

        bookDao.delete(id);
    }

    private void checkOnNull(Long id, Book book) {

        if (book == null) {
            log.info("Book was not found by id: {}", id);
            throw new NotFoundException("This book was not found by id: " + id);
        }
    }
}
