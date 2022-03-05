/*
 * @author S.Maevsky
 * @since 04.03.2022, 16:21
 * @version V 1.0.0
 */

package com.example.task04app.service;

import com.example.task04app.external.openlibrary.dto.BookDbAndOpenLibraryDtoList;

/**
 * The interface Book open library service.
 */
public interface BookOpenLibraryService {

    /**
     * Gets book dtos by author.
     *
     * @param author the author
     * @return the list of books from database and openlibrary.org
     */
    BookDbAndOpenLibraryDtoList getBookDtosByAuthor(String author);

}
