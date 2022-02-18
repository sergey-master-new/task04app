package com.example.task04app.service.impl;

import com.example.task04app.dto.BookDto;
import com.example.task04app.external.dto.BookDbAndOpenLibraryDtoList;
import com.example.task04app.external.dto.BookOpenLibraryDto;
import com.example.task04app.external.impl.OpenLibraryExchangeClientImpl;
import com.example.task04app.service.BookService;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
public class BookOpenLibraryServiceImplTest {

    @Mock
    OpenLibraryExchangeClientImpl exchangeClient;

    @Mock
    private BookService bookService;

    @InjectMocks
    private BookOpenLibraryServiceImpl bookOpenLibraryService;

    @Test
    public void testGetBookDtosByAuthor() {

        when(bookService.getBookDtosByAuthor(anyString())).thenReturn(getBookDtosForTest());

        when(exchangeClient.getBookDtosByAuthor(anyString())).thenReturn(getBookOpenLibraryDtosForTest());

        BookDbAndOpenLibraryDtoList bookDbAndOpenLibraryDtos = getBookDbAndOpenLibraryDtos();
        BookDbAndOpenLibraryDtoList result = bookOpenLibraryService.getBookDtosByAuthor("Толстой");

        assertEquals(bookDbAndOpenLibraryDtos, result);

        verify(bookService).getBookDtosByAuthor(anyString());

        verify(exchangeClient).getBookDtosByAuthor(anyString());
    }

    private List<BookDto> getBookDtosForTest() {

        BookDto bookDtoTest = getBookDtoForTest();
        BookDto bookDtoTest2 = getBookDto2ForTest();
        List<BookDto> bookDtos = Arrays.asList(bookDtoTest, bookDtoTest2);

        return bookDtos;
    }

    private BookDto getBookDtoForTest() {

        return BookDto.builder()
                .id(1L)
                .isbn("978-5-389-07123-0")
                .name("Война и мир")
                .author("Лев Николаевич Толстой")
                .page(1408)
                .weightInGrams(1020)
                .priceInKopecks(2000)
                .build();
    }

    private BookDto getBookDto2ForTest() {

        return BookDto.builder()
                .id(2L)
                .isbn("978-5-389-04935-2")
                .name("Анна Каренина")
                .author("Лев Николаевич Толстой")
                .page(864)
                .weightInGrams(571)
                .priceInKopecks(1150)
                .build();
    }

    private BookDbAndOpenLibraryDtoList getBookDbAndOpenLibraryDtos(){

        BookDbAndOpenLibraryDtoList bookDbAndOpenLibraryDtos = new BookDbAndOpenLibraryDtoList();
        bookDbAndOpenLibraryDtos.getBookDtoList().addAll(getBookDtosForTest());
        bookDbAndOpenLibraryDtos.getBookOpenLibraryDtoList().add(getBookOpenLibraryDtoForTest());

        return bookDbAndOpenLibraryDtos;
    }

    private List<BookOpenLibraryDto> getBookOpenLibraryDtosForTest() {

        List<BookOpenLibraryDto> bookOpenLibraryDtosTest = new ArrayList<>();
        bookOpenLibraryDtosTest.add(getBookOpenLibraryDtoForTest());

        return bookOpenLibraryDtosTest;
    }

    private BookOpenLibraryDto getBookOpenLibraryDtoForTest() {

        ArrayList<String> isbnTest = new ArrayList<>();
        isbnTest.add("978-5-389-07123-0");
        ArrayList<String> author_nameTest = new ArrayList<>();
        author_nameTest.add("Лев Николаевич Толстой");

        return BookOpenLibraryDto.builder()
                .isbn(isbnTest)
                .title("Война и мир")
                .author_name(author_nameTest)
                .number_of_pages_median(1408)
                .build();
    }
}
