package com.example.task04app.service.impl;

import com.example.task04app.dto.BookDto;
import com.example.task04app.exception.DataBaseSQLException;
import com.example.task04app.exception.NotFoundException;
import com.example.task04app.mapper.dto.BookMapper;
import com.example.task04app.model.Book;
import com.example.task04app.persistance.BookDao;
import com.example.task04app.service.BookService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
public class BookServiceImpl implements BookService {

    private final BookDao bookDao;

    private final BookMapper mapper;

    @Override
    public BookDto getBookDtoById(Long id) {

        return tryGetBookDtoByIdAndCheckOnNull(id);
    }

    @Override
    public List<BookDto> getAllBookDtos() {

        return tryGetAllBookDtosAndCheckOnEmpty();
    }

    @Override
    public BookDto saveBookDto(BookDto bookDto) {

        return trySaveBookDtoAndCheckOnNullWhenSave(bookDto);
    }

    @Override
    public BookDto updateBookDto(BookDto bookDto) {

        return tryUpdateBookDtoAndCheckOnNull(bookDto);
    }

    @Override
    public boolean deleteBook(Long id) {

        return tryDeleteBookAndCheckDelete(id);
    }

    private boolean tryDeleteBookAndCheckDelete(Long id) {

        boolean checkDelete;

        try {
            checkDelete = bookDao.delete(id);

        } catch (SQLException ex) {

            log.info("Exception when handling delete book: {}", ex.getMessage());
            throw new DataBaseSQLException("Exception when handling delete book. Please try again.");
        }

        if (!checkDelete) {
            log.info("Book was not found by id: {}", id);
            throw new NotFoundException("This book was not found by id: " + id);
        }
        return true;
    }

    private BookDto tryUpdateBookDtoAndCheckOnNull(BookDto bookDto) {

        Book book = mapper.toBook(bookDto);
        Book bookUpdated;

        try {

            bookUpdated = bookDao.update(book);
            checkOnNull(bookDto.getId(), bookUpdated);

        } catch (EmptyResultDataAccessException ex) {

            log.info("Exception when handling update book." +
                    " Book by id= {} was not found. Message: {}", bookDto.getId(), ex.getMessage());
            throw new NotFoundException(String.format("Book by id= %d was not found", bookDto.getId()));

        } catch (SQLException ex) {

            log.info("Exception when handling update book: {}", ex.getMessage());
            throw new DataBaseSQLException("Exception when handling update book. Please try again.");
        }

        return mapper.toBookDto(bookUpdated);
    }

    private BookDto trySaveBookDtoAndCheckOnNullWhenSave(BookDto bookDto) {

        Book book = mapper.toBook(bookDto);
        Book bookSaved;

        try {

            bookSaved = bookDao.save(book);
            checkOnNullWhenSave(bookSaved);

        } catch (SQLException ex) {

            log.info("Exception when handling save book: {}", ex.getMessage());
            throw new DataBaseSQLException("Exception when handling save book. Please try again.");
        }

        return mapper.toBookDto(bookSaved);
    }

    private List<BookDto> tryGetAllBookDtosAndCheckOnEmpty() {

        List<Book> bookList;

        try {

            bookList = bookDao.getAll();
            checkOnEmpty(bookList);

        } catch (SQLException ex) {

            log.info("Exception when handling save book: {}", ex.getMessage());
            throw new DataBaseSQLException("Exception when handling save book. Please try again.");
        }

        List<BookDto> bookDtoList = mapper.mapToBookDtoList(bookList);

        return bookDtoList;
    }

    private BookDto tryGetBookDtoByIdAndCheckOnNull(Long id) {

        Book book;

        try {

            book = bookDao.getById(id);
            checkOnNull(id, book);

        } catch (EmptyResultDataAccessException ex) {

            log.info("Exception when handling get book." +
                    " Book by id= {} was not found. Message: {}", id, ex.getMessage());
            throw new NotFoundException(String.format("Book by id= %d was not found", id));

        } catch (SQLException ex) {

            log.info("Exception when handling get book: {}", ex.getMessage());
            throw new DataBaseSQLException("Exception when handling get book. Please try again.");
        }

        return mapper.toBookDto(book);
    }

    private void checkOnEmpty(List<Book> bookList) {

        if (bookList.isEmpty()) {

            log.info("The books were not found.");
            throw new NotFoundException("The books were not found. The library is empty.");
        }
    }

    private void checkOnNull(Long id, Book book) {

        if (book == null) {

            log.info("Book was not found by id: {}", id);
            throw new NotFoundException("This book was not found by id: " + id);
        }
    }

    private void checkOnNullWhenSave(Book book) {

        if (book == null) {

            log.info("Exception when save book by id. Saved book is null");
            throw new DataBaseSQLException("There was an exception during save. " +
                    "Please check the saving of the book in the search and try again.");
        }
    }
}
