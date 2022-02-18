package com.example.task04app.external;

import com.example.task04app.external.config.OpenLibraryConfig;
import com.example.task04app.external.dto.BookOpenLibraryDto;
import com.example.task04app.external.impl.OpenLibraryExchangeClientImpl;
import com.example.task04app.external.pojo.ResponseBookOpenLibrary;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class OpenLibraryExchangeClientTest {

    @Mock
    OpenLibraryConfig openLibraryConfig;

    @Mock
    RestTemplate restTemplate;

    @InjectMocks
    private OpenLibraryExchangeClientImpl exchangeClient;

    @Test
    void testGetBookDtosByAuthor() {

        ResponseBookOpenLibrary responseBookOpenLibraryTest = getResponseBookOpenLibrary();
        List<BookOpenLibraryDto> bookOpenLibraryDtosTest = getBookOpenLibraryDtosForTest();

        when(restTemplate.getForObject(Mockito.eq(openLibraryConfig.getBaseUrl() + "/search.json?author=Толстой"),
                Mockito.eq(ResponseBookOpenLibrary.class))).thenReturn(responseBookOpenLibraryTest);

        List<BookOpenLibraryDto> result = exchangeClient.getBookDtosByAuthor("Толстой");

        assertEquals(bookOpenLibraryDtosTest, result);

        verify(restTemplate).getForObject(
                Mockito.eq(openLibraryConfig.getBaseUrl() + "/search.json?author=Толстой"),
                Mockito.eq(ResponseBookOpenLibrary.class));
    }

    private ResponseBookOpenLibrary getResponseBookOpenLibrary(){

        ResponseBookOpenLibrary responseBookOpenLibrary = new ResponseBookOpenLibrary();
        responseBookOpenLibrary.getBookOpenLibraryDtoList().add(getBookOpenLibraryDtoForTest());

        return responseBookOpenLibrary;
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
