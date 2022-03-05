/*
 * @author S.Maevsky
 * @since 04.03.2022, 16:21
 * @version V 1.0.0
 */

package com.example.task04app.service.impl;

import com.example.task04app.dto.BookDto;
import com.example.task04app.exception.DataBaseAccessException;
import com.example.task04app.exception.NotFoundException;
import com.example.task04app.external.alfabank.dto.BookPriceInCurrenciesDto;
import com.example.task04app.external.alfabank.pojo.Rate;
import com.example.task04app.mapper.BookMapper;
import com.example.task04app.entity.Book;
import com.example.task04app.persistance.BookDao;
import com.example.task04app.service.BookService;
import com.example.task04app.service.external.AlfaBankExchangeClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Book service.
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {

    private final BookDao bookDao;

    private final BookMapper mapper;

    private final AlfaBankExchangeClient alfaBankExchangeClient;

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

        return trySaveBookDtoAndCheckOnNull(bookDto);
    }

    @Override
    public BookDto updateBookDto(BookDto bookDto) {

        return tryUpdateBookDtoAndCheckOnNull(bookDto);
    }

    @Override
    public boolean deleteBook(Long id) {

        return tryDeleteBook(id);
    }

    @Override
    public List<BookDto> getBookDtosByAuthor(String author) {

        return tryGetBookDtosByAuthorAndCheckOnEmpty(author);
    }

    @Override
    public List<BookDto> getBookDtosByTitle(String title){

        return tryGetBookDtosByTitleAndCheckOnEmpty(title);
    }

    @Override
    public List<BookPriceInCurrenciesDto> getBookDtosInCurrenciesByTitle(String title){

        return getBookPriceInCurrenciesDtos(title);
    }

    private List<BookPriceInCurrenciesDto> getBookPriceInCurrenciesDtos(String title) {

        List<BookDto> bookDtoList = getBookDtosByTitle(title);
        List<Rate> rateList = alfaBankExchangeClient.getRates();

        return addPriceInCurrenciesToBookDtos(bookDtoList, rateList);
    }

    private List<BookPriceInCurrenciesDto> addPriceInCurrenciesToBookDtos(List<BookDto> bookDtoList,
                                                                          List<Rate> rateList){

        List<BookPriceInCurrenciesDto> bookPriceInCurrenciesDtoList = new ArrayList<>();

        for (BookDto bookDto: bookDtoList) {

            BookPriceInCurrenciesDto bookCurrenciesDto = new BookPriceInCurrenciesDto();
            bookCurrenciesDto.setBookDto(bookDto);
            addPriceInCurrency(bookCurrenciesDto, rateList, bookDto);
            bookPriceInCurrenciesDtoList.add(bookCurrenciesDto);
        }

        return bookPriceInCurrenciesDtoList;
    }

    private void addPriceInCurrency(BookPriceInCurrenciesDto bookCurrenciesDto, List<Rate> rateList, BookDto bookDto) {

        int scaleFour = 4;
        int scaleTwo = 2;

        bookCurrenciesDto.getPriceInCurrenciesMap().put("BYN", BigDecimal.valueOf(bookDto.getPriceInKopecks())
                .divide(BigDecimal.valueOf(100), scaleTwo, RoundingMode.HALF_UP));

        for (Rate rate: rateList){

            addPricesInCurrencies(bookCurrenciesDto, bookDto, scaleFour, scaleTwo, rate);
        }
    }

    private void addPricesInCurrencies(BookPriceInCurrenciesDto bookCurrenciesDto, BookDto bookDto,
                                       int scaleFour, int scaleTwo, Rate rate) {

        if (rate.getBuyIso().equals("BYN")){

            BigDecimal divisor = rate.getSellRate()
                    .divide(BigDecimal.valueOf(rate.getQuantity()),scaleFour, RoundingMode.HALF_UP);
            BigDecimal priceInRubles = (BigDecimal
                    .valueOf(bookDto.getPriceInKopecks()))
                    .divide(BigDecimal.valueOf(100),scaleTwo, RoundingMode.HALF_UP);
            BigDecimal currencyPrice = priceInRubles.divide(divisor, scaleTwo, RoundingMode.HALF_UP);
            bookCurrenciesDto.getPriceInCurrenciesMap().put(rate.getSellIso(), currencyPrice);
        }
    }

    private boolean tryDeleteBook(Long id) {

        try {

            bookDao.delete(id);

        } catch (DataAccessException ex) {

            log.error("Exception when handling delete book: {}", ex.getMessage());
            throw new DataBaseAccessException("Exception when handling delete book. Please try again.");
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

            log.error("Exception when handling update book." +
                    " Book by id= {} was not found. Message: {}", bookDto.getId(), ex.getMessage());
            throw new NotFoundException(String.format("Book by id= %d was not found", bookDto.getId()));

        } catch (DataAccessException ex) {

            log.error("Exception when handling update book: {}", ex.getMessage());
            throw new DataBaseAccessException("Exception when handling update book. Please try again.");
        }

        return mapper.toBookDto(bookUpdated);
    }

    private BookDto trySaveBookDtoAndCheckOnNull(BookDto bookDto) {

        Book book = mapper.toBook(bookDto);
        Book bookSaved;

        try {

            bookSaved = bookDao.save(book);
            checkOnNullWhenSave(bookSaved);

        } catch (DataAccessException ex) {

            log.error("Exception when handling save book: {}", ex.getMessage());
            throw new DataBaseAccessException("Exception when handling save book. Please try again.");
        }

        return mapper.toBookDto(bookSaved);
    }

    private List<BookDto> tryGetAllBookDtosAndCheckOnEmpty() {

        List<Book> bookList;

        try {

            bookList = bookDao.getAll();
            checkOnEmpty(bookList);

        } catch (DataAccessException ex) {

            log.error("Exception when handling get all books: {}", ex.getMessage());
            throw new DataBaseAccessException("Exception when handling get all books. Please try again.");
        }

        return mapper.mapToBookDtoList(bookList);
    }

    private List<BookDto> tryGetBookDtosByAuthorAndCheckOnEmpty(String author){

        List<Book> bookList;

        try {

            bookList = bookDao.getByAuthor(author);
            checkOnEmpty(bookList);

        } catch (DataAccessException ex) {

            log.error("Exception when handling get books by author: {}", ex.getMessage());
            throw new DataBaseAccessException("Exception when handling get books by author. Please try again.");
        }

        return mapper.mapToBookDtoList(bookList);
    }

    private List<BookDto> tryGetBookDtosByTitleAndCheckOnEmpty(String title){

        List<Book> bookList;

        try {

            bookList = bookDao.getByTitle(title);
            checkOnEmpty(bookList);

        } catch (DataAccessException ex) {

            log.error("Exception when handling get books by title: {}", ex.getMessage());
            throw new DataBaseAccessException("Exception when handling get books by title. Please try again.");
        }

        return mapper.mapToBookDtoList(bookList);
    }

    private BookDto tryGetBookDtoByIdAndCheckOnNull(Long id) {

        Book book;

        try {

            book = bookDao.getById(id);
            checkOnNull(id, book);

        } catch (EmptyResultDataAccessException ex) {

            log.error("Exception when handling get book." +
                    " Book by id= {} was not found. Message: {}", id, ex.getMessage());
            throw new NotFoundException(String.format("Book by id= %d was not found", id));

        } catch (DataAccessException ex) {

            log.error("Exception when handling get book: {}", ex.getMessage());
            throw new DataBaseAccessException("Exception when handling get book. Please try again.");
        }

        return mapper.toBookDto(book);
    }

    private void checkOnEmpty(List<Book> bookList) {

        if (bookList.isEmpty()) {

            log.error("The books were not found.");
            throw new NotFoundException("The books were not found.");
        }
    }

    private void checkOnNull(Long id, Book book) {

        if (book == null) {

            log.error("Book was not found by id: {}", id);
            throw new NotFoundException("This book was not found by id: " + id);
        }
    }

    private void checkOnNullWhenSave(Book book) {

        if (book == null) {

            log.error("Exception when save book by id. Saved book is null");
            throw new DataBaseAccessException("There was an exception during save. " +
                    "Please check the saving of the book in the search and try again.");
        }
    }
}
