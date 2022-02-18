package com.example.task04app.controller;

import com.example.task04app.dto.BookDto;
import com.example.task04app.external.dto.BookDbAndOpenLibraryDtoList;
import com.example.task04app.external.dto.BookOpenLibraryDto;
import com.example.task04app.service.impl.BookOpenLibraryServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest
public class BookOpenLibraryControllerTest {

    @MockBean
    private BookOpenLibraryServiceImpl bookOpenLibraryService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetBookDtosByAuthor() throws Exception {

        BookDbAndOpenLibraryDtoList responseBookDtos = new BookDbAndOpenLibraryDtoList();
        responseBookDtos.getBookDtoList().addAll(getBookDtosForTest());
        responseBookDtos.getBookOpenLibraryDtoList().add(getBookOpenLibraryDtoForTest());

        when(bookOpenLibraryService.getBookDtosByAuthor(anyString())).thenReturn(responseBookDtos);

        mockMvc.perform(get("/open-library?author=Толстой"))
                .andExpect(jsonPath("$.bookDtoList[0].id").value(1L))
                .andExpect(jsonPath("$.bookDtoList[0].isbn").value("978-5-389-07123-0"))
                .andExpect(jsonPath("$.bookDtoList[0].name").value("Война и мир"))
                .andExpect(jsonPath("$.bookDtoList[0].author").value("Лев Николаевич Толстой"))
                .andExpect(jsonPath("$.bookDtoList[0].page").value(1408))
                .andExpect(jsonPath("$.bookDtoList[0].weightInGrams").value(1020))
                .andExpect(jsonPath("$.bookDtoList[0].priceInKopecks").value(2000))
                .andExpect(jsonPath("$.bookDtoList[1].id").value(2L))
                .andExpect(jsonPath("$.bookDtoList[1].isbn").value("978-5-389-04935-2"))
                .andExpect(jsonPath("$.bookDtoList[1].name").value("Анна Каренина"))
                .andExpect(jsonPath("$.bookDtoList[1].author").value("Лев Николаевич Толстой"))
                .andExpect(jsonPath("$.bookDtoList[1].page").value(864))
                .andExpect(jsonPath("$.bookDtoList[1].weightInGrams").value(571))
                .andExpect(jsonPath("$.bookDtoList[1].priceInKopecks").value(1150))
                .andExpect(jsonPath("$.bookOpenLibraryDtoList[0].isbn[0]")
                        .value("978-5-389-07123-0"))
                .andExpect(jsonPath("$.bookOpenLibraryDtoList[0].title")
                        .value("Война и мир"))
                .andExpect(jsonPath("$.bookOpenLibraryDtoList[0].author_name[0]")
                        .value("Лев Николаевич Толстой"))
                .andExpect(jsonPath("$.bookOpenLibraryDtoList[0].number_of_pages_median")
                        .value(1408))
                .andExpect(status().isOk());

        verify(bookOpenLibraryService).getBookDtosByAuthor(anyString());
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
