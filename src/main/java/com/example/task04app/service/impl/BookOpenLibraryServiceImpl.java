/*
 * @author S.Maevsky
 * @since 04.03.2022, 16:20
 * @version V 1.0.0
 */

package com.example.task04app.service.impl;

import com.example.task04app.exception.NotFoundException;
import com.example.task04app.service.external.OpenLibraryExchangeClient;
import com.example.task04app.external.openlibrary.dto.BookDbAndOpenLibraryDtoList;
import com.example.task04app.external.openlibrary.dto.BookOpenLibraryDto;
import com.example.task04app.service.BookOpenLibraryService;
import com.example.task04app.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * The type Book open library service.
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class BookOpenLibraryServiceImpl implements BookOpenLibraryService {

    private final BookService bookService;

    private final OpenLibraryExchangeClient openLibraryExchangeClient;

    @Override
    public BookDbAndOpenLibraryDtoList getBookDtosByAuthor(String author) {

        BookDbAndOpenLibraryDtoList bookDbAndOpenLibraryDtoList = new BookDbAndOpenLibraryDtoList();
        addBookDtosFromOpenLibrary(bookDbAndOpenLibraryDtoList, author);
        tryAddBookDtosFromDB(bookDbAndOpenLibraryDtoList, author);
        checkOnEmpty(bookDbAndOpenLibraryDtoList, author);

        return bookDbAndOpenLibraryDtoList;
    }

    private void addBookDtosFromOpenLibrary(BookDbAndOpenLibraryDtoList bookDbAndOpenLibraryDtoList,
                                            String author) {

        List<BookOpenLibraryDto> bookOpenLibraryDtoList = openLibraryExchangeClient.getBookDtosByAuthor(author);
        addBookOpenLibraryDtoList(bookOpenLibraryDtoList,
                bookDbAndOpenLibraryDtoList);
    }

    private void addBookOpenLibraryDtoList (List<BookOpenLibraryDto> bookOpenLibraryDtoList,
                                            BookDbAndOpenLibraryDtoList bookDbAndOpenLibraryDtoList) {

        if (bookOpenLibraryDtoList != null) {

            bookDbAndOpenLibraryDtoList.getBookOpenLibraryDtoList().addAll(bookOpenLibraryDtoList);
        }
    }

    private void tryAddBookDtosFromDB(BookDbAndOpenLibraryDtoList responseBookDtoList, String author) {

        try {

            responseBookDtoList.getBookDtoList().addAll(bookService.getBookDtosByAuthor(author));

        } catch (NotFoundException ex) {

            log.info("Books by author= {} were not found in DB. Message: {}", author, ex.getMessage());
        }
    }

    private void checkOnEmpty(BookDbAndOpenLibraryDtoList responseBookDtoList, String author) {

        if (responseBookDtoList.getBookDtoList().isEmpty() &
                responseBookDtoList.getBookOpenLibraryDtoList().isEmpty()) {

            throw new NotFoundException(String.format("The books by author = %s were not found in OpenLibrary and " +
                    "data base.", author));
        }
    }
}
