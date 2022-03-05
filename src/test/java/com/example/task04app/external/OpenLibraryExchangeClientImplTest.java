package com.example.task04app.external;

import com.example.task04app.external.openlibrary.config.OpenLibraryConfig;
import com.example.task04app.external.openlibrary.dto.BookOpenLibraryDto;
import com.example.task04app.external.openlibrary.serviceimpl.OpenLibraryExchangeClientImpl;
import com.example.task04app.external.openlibrary.pojo.ResponseBookOpenLibrary;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class OpenLibraryExchangeClientImplTest {

    @Value("integration.openlibrary.baseUrl")
    String baseUrl;

    @Mock
    OpenLibraryConfig openLibraryConfig;

    @Mock
    RestTemplate restTemplate;

    @InjectMocks
    private OpenLibraryExchangeClientImpl exchangeClient;

    @Test
    void testGetBookDtosByAuthor() throws NoSuchFieldException, IllegalAccessException {

        ResponseBookOpenLibrary responseBookOpenLibraryTest = getResponseBookOpenLibrary();
        List<BookOpenLibraryDto> bookOpenLibraryDtosTest = getBookOpenLibraryDtosForTest();
        Map<String, String> uriParameter = new HashMap<>();
        uriParameter.put("author", "Толстой");

        when(openLibraryConfig.getBaseUrl()).thenReturn(baseUrl);

        Field field = OpenLibraryExchangeClientImpl.class.getDeclaredField("END_POINT_GET_BY_AUTHOR");
        field.setAccessible(true);
        String endPoint = field.get(null).toString();

        when(restTemplate.getForObject(
                Mockito.eq( baseUrl + endPoint),
                Mockito.eq(ResponseBookOpenLibrary.class), Mockito.eq(uriParameter)))
                .thenReturn(responseBookOpenLibraryTest);

        List<BookOpenLibraryDto> result = exchangeClient.getBookDtosByAuthor("Толстой");

        assertEquals(bookOpenLibraryDtosTest, result);

        verify(restTemplate).getForObject(
                Mockito.eq( baseUrl + endPoint),
                Mockito.eq(ResponseBookOpenLibrary.class),
                Mockito.eq(uriParameter));
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
        ArrayList<String> authorNameTest = new ArrayList<>();
        authorNameTest.add("Лев Николаевич Толстой");

        return BookOpenLibraryDto.builder()
                .isbn(isbnTest)
                .title("Война и мир")
                .authorName(authorNameTest)
                .numberOfPagesMedian(1408)
                .build();
    }
}
