package com.example.task04app.external;

import com.example.task04app.exception.ExternalRequestException;
import com.example.task04app.exception.NotFoundException;
import com.example.task04app.external.dto.BookDbAndOpenLibraryDtos;
import com.example.task04app.external.dto.BookOpenLibraryDto;
import com.example.task04app.external.dto.BookOpenLibraryDtos;
import com.example.task04app.service.BookService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
public class OpenLibraryExchangeClient {

    private final BookService bookService;

    private final RestTemplate restTemplate;

    public BookDbAndOpenLibraryDtos getBookDtosByAuthor(String author) {

        BookDbAndOpenLibraryDtos responseBookDtoList = new BookDbAndOpenLibraryDtos();

        tryAddBookDtosFromDB(responseBookDtoList, author);
        tryAddBookDtosFromOpenLibrary(responseBookDtoList, author);

        checkOnEmpty(responseBookDtoList, author);
        checkOnNullBookOpenLibraryDtos(responseBookDtoList);

        return responseBookDtoList;
    }

    private void tryAddBookDtosFromDB(BookDbAndOpenLibraryDtos responseBookDtoList, String author) {

        try {

            responseBookDtoList.getBookDtoList().addAll(bookService.getBookDtosByAuthor(author));

        } catch (NotFoundException ex) {

            log.info("Books by author= {} were not found in DB. Message: {}", author, ex.getMessage());
        }
    }

    private void tryAddBookDtosFromOpenLibrary(BookDbAndOpenLibraryDtos responseBookDtoList, String author) {

        String URI_OPEN_LIBRARY_BY_AUTHOR = "http://openlibrary.org/search.json?author=";
        BookOpenLibraryDtos response;

        try {

            response = restTemplate.getForObject(URI_OPEN_LIBRARY_BY_AUTHOR + author,
                    BookOpenLibraryDtos.class);

        } catch (Exception ex) {

            log.info("Exception when handling get books from OpenLibrary by author: {}", author);
            throw new ExternalRequestException("Exception when handling get books from OpenLibrary. " +
                    "Please try again later.");
        }

        if (response != null) {

            List<BookOpenLibraryDto> bookOpenLibraryDtoList = response.getDocs();
            responseBookDtoList.getBookOpenLibraryDtoList().addAll(bookOpenLibraryDtoList);
        }
    }

    private void checkOnEmpty(BookDbAndOpenLibraryDtos responseBookDtoList, String author) {

        if (responseBookDtoList.getBookDtoList().isEmpty() &
                responseBookDtoList.getBookOpenLibraryDtoList().isEmpty()) {

            throw new NotFoundException(String.format("The books by author = %s were not found in OpenLibrary and " +
                    "data base.", author));
        }
    }

    private void checkOnNullBookOpenLibraryDtos(BookDbAndOpenLibraryDtos responseBookDtoList) {

        for (BookOpenLibraryDto book : responseBookDtoList.getBookOpenLibraryDtoList()) {

            if (book.getIsbn() == null) {

                book.setIsbn(new ArrayList<>());
            }

            if (book.getNumber_of_pages_median() == null) {

                book.setNumber_of_pages_median(0);
            }
        }
    }
}