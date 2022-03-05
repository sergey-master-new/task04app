/*
 * @author S.Maevsky
 * @since 04.03.2022, 16:15
 * @version V 1.0.0
 */

package com.example.task04app.mapper;

import com.example.task04app.dto.BookDto;
import com.example.task04app.entity.Book;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.List;

/**
 * The interface Book mapper.
 */
@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface BookMapper {

    /**
     * To book dto book dto.
     *
     * @param book the book
     * @return the book dto
     */
    @Named(value = "bookDto")
    BookDto toBookDto (Book book);

    /**
     * To book book.
     *
     * @param bookDto the book dto
     * @return the book
     */
    @Named(value = "book")
    Book toBook (BookDto bookDto);

    /**
     * Map to book list list.
     *
     * @param bookDtoListDto the book dto list dto
     * @return the list
     */
    @IterableMapping(qualifiedByName = "book")
    List<Book> mapToBookList(List<BookDto> bookDtoListDto);

    /**
     * Map to book dto list list.
     *
     * @param bookList the book list
     * @return the list
     */
    @IterableMapping(qualifiedByName = "bookDto")
    List<BookDto> mapToBookDtoList(List<Book> bookList);
}
