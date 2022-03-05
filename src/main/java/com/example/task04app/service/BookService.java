/*
 * @author S.Maevsky
 * @since 04.03.2022, 16:22
 * @version V 1.0.0
 */

package com.example.task04app.service;

import com.example.task04app.dto.BookDto;
import com.example.task04app.external.alfabank.dto.BookPriceInCurrenciesDto;

import java.util.List;

/**
 * The interface Book service.
 */
public interface BookService {

    /**
     * Gets book dto by id.
     *
     * @param id the id
     * @return the book dto by id
     */
    BookDto getBookDtoById(Long id);

    /**
     * Gets all book dtos.
     *
     * @return the all book dtos
     */
    List<BookDto> getAllBookDtos();

    /**
     * Save book dto book dto.
     *
     * @param bookDto the book dto
     * @return the book dto
     */
    BookDto saveBookDto(BookDto bookDto);

    /**
     * Update book dto book dto.
     *
     * @param bookDto the book dto
     * @return the book dto
     */
    BookDto updateBookDto(BookDto bookDto);

    /**
     * Delete book boolean.
     *
     * @param id the id
     * @return the boolean
     */
    boolean deleteBook(Long id);

    /**
     * Gets book dtos by author.
     *
     * @param author the author
     * @return the book dtos by author
     */
    List<BookDto> getBookDtosByAuthor(String author);

    /**
     * Gets book dtos by title.
     *
     * @param title the title
     * @return the book dtos by title
     */
    List<BookDto> getBookDtosByTitle(String title);

    /**
     * Gets book dtos in currencies EUR, USD, RUB by title.
     *
     * @param title the title
     * @return the book dtos in currencies by title
     */
    List<BookPriceInCurrenciesDto> getBookDtosInCurrenciesByTitle(String title);
}
