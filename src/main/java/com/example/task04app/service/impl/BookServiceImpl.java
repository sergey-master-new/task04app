package com.example.task04app.service.impl;

import com.example.task04app.dto.BookDto;
import com.example.task04app.repository.DataBaseCache;
import com.example.task04app.service.BookService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Override
    public BookDto getBookDtoById(Long id) {
        BookDto bookDto = DataBaseCache.getBook(id);
        return bookDto;
    }

    @Override
    public List<BookDto> getAllBookDtos() {
        List <BookDto> bookDtoList = new ArrayList<>(DataBaseCache.getCacheBookRepository().values());
        System.out.println(bookDtoList);
        return bookDtoList;
    }

    @Override
    public BookDto saveBookDto(BookDto bookDto) {
        Long idCurrentBookDto = DataBaseCache.getIdCount() + 1;
        bookDto.setId(idCurrentBookDto);
        DataBaseCache.setBook(idCurrentBookDto, bookDto);
        DataBaseCache.setIdCount(idCurrentBookDto);
        return DataBaseCache.getCacheBookRepository().get(idCurrentBookDto);
    }

    @Override
    public BookDto updateBookDto(BookDto bookDto) {
        DataBaseCache.setBook(bookDto.getId(), bookDto);
        return DataBaseCache.getCacheBookRepository().get(bookDto.getId());
    }

    @Override
    public boolean containsBook(Long id) {
        return DataBaseCache.getCacheBookRepository().containsKey(id);
    }

    @Override
    public void deleteBook(Long id) {
        DataBaseCache.deleteBook(id);
    }
}
