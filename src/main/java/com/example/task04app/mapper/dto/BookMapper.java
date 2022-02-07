package com.example.task04app.mapper.dto;

import com.example.task04app.dto.BookDto;
import com.example.task04app.model.Book;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface BookMapper {

    @Named(value = "bookDto")
    BookDto toBookDto(Book book);

    @Named(value = "book")
    Book toBook(BookDto bookDto);

    @IterableMapping(qualifiedByName = "book")
    List<Book> mapToBookList(List<BookDto> bookDtoListDto);

    @IterableMapping(qualifiedByName = "bookDto")
    List<BookDto> mapToBookDtoList(List<Book> bookList);

}
