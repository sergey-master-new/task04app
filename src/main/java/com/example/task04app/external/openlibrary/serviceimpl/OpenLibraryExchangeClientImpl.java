/*
 * @author S.Maevsky
 * @since 04.03.2022, 16:11
 * @version V 1.0.0
 */

package com.example.task04app.external.openlibrary.serviceimpl;

import com.example.task04app.exception.ExternalRequestException;
import com.example.task04app.exception.ExternalResponseException;
import com.example.task04app.service.external.OpenLibraryExchangeClient;
import com.example.task04app.external.openlibrary.config.OpenLibraryConfig;
import com.example.task04app.external.openlibrary.dto.BookOpenLibraryDto;
import com.example.task04app.external.openlibrary.pojo.ResponseBookOpenLibrary;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The type Open library exchange client.
 */
@Slf4j
@Getter
@Service
public class OpenLibraryExchangeClientImpl implements OpenLibraryExchangeClient {

    private final RestTemplate restTemplate;

    private final OpenLibraryConfig openLibraryConfig;

    private static final String END_POINT_GET_BY_AUTHOR = "/search.json?author={author}";

    /**
     * Instantiates a new Open library exchange client.
     *
     * @param restTemplate      the rest template for openlibrary
     * @param openLibraryConfig the open library config
     */
    public OpenLibraryExchangeClientImpl(@Qualifier("restTemplateOpenLibrary") RestTemplate restTemplate,
                                         OpenLibraryConfig openLibraryConfig) {
        this.restTemplate = restTemplate;
        this.openLibraryConfig = openLibraryConfig;
    }

    public List<BookOpenLibraryDto> getBookDtosByAuthor(String author) {

        return tryGetBookDtosFromOpenLibrary(author);
    }

    private List<BookOpenLibraryDto> tryGetBookDtosFromOpenLibrary(String author) {

        ResponseBookOpenLibrary response;

        try {

            Map<String, String> uriParameter = new HashMap<>();
            uriParameter.put("author", author);
            response = restTemplate.getForObject(openLibraryConfig.getBaseUrl() + END_POINT_GET_BY_AUTHOR,
                    ResponseBookOpenLibrary.class, uriParameter);

        } catch (Exception ex) {

            log.debug("Exception when handling get books from OpenLibrary by author: {}", author);
            throw new ExternalRequestException("Exception when handling get books from OpenLibrary. " +
                    "Please try again later.");
        }

        return getBookOpenLibraryDtoList(response);
    }

    private List<BookOpenLibraryDto> getBookOpenLibraryDtoList(ResponseBookOpenLibrary response) {

        if (response != null) {

            if (response.getBookOpenLibraryDtoList() != null) {

                return checkOnNullFields(response);
            }
        }

        return new ArrayList<>();
    }

    private List<BookOpenLibraryDto> checkOnNullFields(ResponseBookOpenLibrary response) {

        if (response == null || response.getBookOpenLibraryDtoList() == null) {

            log.error("Exception when getting data from openlibrary. Response = null");
            throw new ExternalResponseException("Exception when getting data from openlibrary.");
        }

        if (!response.getBookOpenLibraryDtoList().isEmpty()) {

            for (BookOpenLibraryDto book : response.getBookOpenLibraryDtoList()) {

                if (book.getIsbn() == null) {

                    book.setIsbn(new ArrayList<>());
                }

                if (book.getNumberOfPagesMedian() == null) {

                    book.setNumberOfPagesMedian(0);
                }
            }
        }

        return response.getBookOpenLibraryDtoList();
    }

}
