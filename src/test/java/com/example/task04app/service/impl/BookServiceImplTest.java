package com.example.task04app.service.impl;

import com.example.task04app.dto.BookDto;
import com.example.task04app.mapper.dto.BookMapper;
import com.example.task04app.model.Book;
import com.example.task04app.persistance.BookDao;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class BookServiceImplTest {

    @Spy
    private BookMapper mapper = Mappers.getMapper(BookMapper.class);

    @Mock
    private BookDao bookDaoMock;

    @InjectMocks
    private BookServiceImpl bookService;

    @Test
    @DisplayName("test \"Get By Id\" should work")
    void testGetById() throws SQLException {

        Book bookTest = getBookForTest();

        Long bookIdTest = bookTest.getId();

        BookDto bookDtoResult = mapper.toBookDto(bookTest);

        Mockito.when(bookDaoMock.getById(Mockito.eq(bookIdTest))).thenReturn(bookTest);

        Assert.assertEquals(bookService.getBookDtoById(bookIdTest), bookDtoResult);

        Mockito.verify(bookDaoMock, Mockito.times(1)).getById(bookIdTest);
    }

    @Test
    @DisplayName("test \"Get All Book Dtos\" should work")
    void testGetAllBookDtos() throws SQLException {

        List<Book> books = getBooksForTest();

        List<BookDto> bookDtosResult = mapper.mapToBookDtoList(books);

        when(bookDaoMock.getAll()).thenReturn(books);

        assertEquals(bookService.getAllBookDtos(), bookDtosResult);

        verify(bookDaoMock).getAll();
    }

    @Test
    @DisplayName("test \"Save Book Dto\" should work")
    void testSaveBookDto() throws SQLException {

        Book bookTest = getBookForTest();

        BookDto bookDto = mapper.toBookDto(bookTest);
        BookDto bookDtoResult = mapper.toBookDto(bookTest);

        when(bookDaoMock.save(Mockito.any(Book.class))).thenReturn(bookTest);

        assertEquals(bookService.saveBookDto(bookDto), bookDtoResult);

        verify(bookDaoMock).save(bookTest);
    }

    @Test
    @DisplayName("test \"Update\" should work")
    void testUpdate() throws SQLException {

        Book bookTest = getBookForTest();

        BookDto bookDto = mapper.toBookDto(bookTest);
        BookDto bookDtoResult = mapper.toBookDto(bookTest);

        doReturn(bookTest).when(bookDaoMock).update(Mockito.any(Book.class));

        assertEquals(bookService.updateBookDto(bookDto), bookDtoResult);

        verify(bookDaoMock).update(bookTest);

        Mockito.verifyNoMoreInteractions(bookDaoMock);
    }

    @Test
    @DisplayName("test \"Delete\" should work")
    void testDelete() throws SQLException {

        Long bookIdTest = 1l;

        when(bookDaoMock.delete(anyLong())).thenReturn(true);

        bookService.deleteBook(bookIdTest);

        verify(bookDaoMock).delete(bookIdTest);
    }


    private List<Book> getBooksForTest(){

        Book bookTest = getBookForTest();
        Book bookTest2 = getBook2ForTest();
        List<Book> books = Arrays.asList(bookTest, bookTest2);

        return books;
    }

    private Book getBookForTest(){

        return Book.builder()
                .id(1L)
                .isbn("978-5-389-07123-0")
                .name("Война и мир")
                .author("Лев Николаевич Толстой")
                .page(1408)
                .weightInGrams(1020)
                .priceInKopecks(2000)
                .build();
    }

    private Book getBook2ForTest(){

        return Book.builder()
                .id(2L)
                .isbn("978-5-389-04935-2")
                .name("Анна Каренина")
                .author("Лев Николаевич Толстой")
                .page(864)
                .weightInGrams(571)
                .priceInKopecks(1150)
                .build();
    }
}
