/*
 * @author S.Maevsky
 * @since 04.03.2022, 16:14
 * @version V 1.0.0
 */

package com.example.task04app.service.external;

import com.example.task04app.external.openlibrary.dto.BookOpenLibraryDto;

import java.util.List;

/**
 * The interface Open library exchange client.
 */
public interface OpenLibraryExchangeClient {

    /**
     * Gets book dtos by author from openlibrary.org.
     *
     * @param author the author
     * @return the list of book dtos by author
     */
    List<BookOpenLibraryDto> getBookDtosByAuthor(String author);

}
