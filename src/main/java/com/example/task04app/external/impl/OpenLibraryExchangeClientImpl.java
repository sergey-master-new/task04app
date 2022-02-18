package com.example.task04app.external.impl;

import com.example.task04app.exception.ExternalRequestException;
import com.example.task04app.external.OpenLibraryExchangeClient;
import com.example.task04app.external.config.OpenLibraryConfig;
import com.example.task04app.external.dto.BookOpenLibraryDto;
import com.example.task04app.external.pojo.ResponseBookOpenLibrary;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@AllArgsConstructor
@Getter
@Service
public class OpenLibraryExchangeClientImpl implements OpenLibraryExchangeClient {

    private final RestTemplate restTemplate;

    private final OpenLibraryConfig openLibraryConfig;

    private static final String END_POINT_GET_BY_AUTHOR = "/search.json?author=";

    public List<BookOpenLibraryDto> getBookDtosByAuthor(String author) {

        List<BookOpenLibraryDto> bookOpenLibraryDtoList = tryGetBookDtosFromOpenLibrary(author);

        return bookOpenLibraryDtoList;
    }

    private List<BookOpenLibraryDto> tryGetBookDtosFromOpenLibrary(String author) {

        ResponseBookOpenLibrary response;

        try {

            response = restTemplate.getForObject(openLibraryConfig.getBaseUrl() + END_POINT_GET_BY_AUTHOR
                            + author, ResponseBookOpenLibrary.class);

        } catch (Exception ex) {

            log.info("Exception when handling get books from OpenLibrary by author: {}", author);
            throw new ExternalRequestException("Exception when handling get books from OpenLibrary. " +
                    "Please try again later.");
        }

        checkOnNullFieldsBookOpenLibraryDtoList(response);

        return response.getBookOpenLibraryDtoList();

    }

    private void checkOnNullFieldsBookOpenLibraryDtoList(ResponseBookOpenLibrary response) {

        if (response.getBookOpenLibraryDtoList() != null) {

            for (BookOpenLibraryDto book : response.getBookOpenLibraryDtoList()) {

                if (book.getIsbn() == null) {

                    book.setIsbn(new ArrayList<>());
                }

                if (book.getNumber_of_pages_median() == null) {

                    book.setNumber_of_pages_median(0);
                }
            }
        }
    }

}
