package com.example.task04app.external;

import com.example.task04app.dto.BookDto;
import com.example.task04app.external.dto.BookDbAndOpenLibraryDtos;
import com.example.task04app.external.dto.BookOpenLibraryDto;
import com.example.task04app.external.dto.BookOpenLibraryDtos;
import com.example.task04app.service.BookService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class OpenLibraryExchangeClientTest {

    @Mock
    RestTemplate restTemplate;

    @Mock
    private BookService bookService;

    @InjectMocks
    private OpenLibraryExchangeClient exchangeClient;

    @Test
    void testGetBookDtosByAuthor() {

        when(bookService.getBookDtosByAuthor(anyString())).thenReturn(getBookDtosForTest());

        when(restTemplate.getForObject(Mockito.eq("http://openlibrary.org/search.json?author=Толстой"),
                Mockito.eq(BookOpenLibraryDtos.class))).thenReturn(getBookOpenLibraryDtosForTest());

        BookDbAndOpenLibraryDtos responseBookDtos = getBookDbAndOpenLibraryDtos();

        BookDbAndOpenLibraryDtos result = exchangeClient.getBookDtosByAuthor("Толстой");

        Assert.assertEquals(responseBookDtos, result);

        verify(bookService).getBookDtosByAuthor(anyString());

        verify(restTemplate).getForObject(Mockito.eq("http://openlibrary.org/search.json?author=Толстой"),
                Mockito.eq(BookOpenLibraryDtos.class));
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

    private BookDbAndOpenLibraryDtos getBookDbAndOpenLibraryDtos() {

        BookDbAndOpenLibraryDtos responseBookDtos = new BookDbAndOpenLibraryDtos();

        responseBookDtos.getBookDtoList().addAll(getBookDtosForTest());
        responseBookDtos.getBookOpenLibraryDtoList().add(getBookOpenLibraryDtoForTest());

        return responseBookDtos;
    }

    private BookOpenLibraryDtos getBookOpenLibraryDtosForTest() {

        List<BookOpenLibraryDto> bookOpenLibraryDtoListTest = new ArrayList<>();
        bookOpenLibraryDtoListTest.add(getBookOpenLibraryDtoForTest());

        BookOpenLibraryDtos bookOpenLibraryDtosTest = new BookOpenLibraryDtos();
        bookOpenLibraryDtosTest.getDocs().add(getBookOpenLibraryDtoForTest());

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
