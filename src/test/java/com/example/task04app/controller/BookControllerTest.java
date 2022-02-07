package com.example.task04app.controller;

import com.example.task04app.dto.BookDto;
import com.example.task04app.service.BookService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest
public class BookControllerTest {

    @MockBean
    private BookService bookService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetBookDtoById() throws Exception {

        BookDto bookDtoTest = getBookDtoForTest();

        when(bookService.getBookDtoById(Mockito.eq(1L))).thenReturn(bookDtoTest);

        mockMvc.perform(get("/book/{id}", 1L))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.isbn").value("978-5-389-07123-0"))
                .andExpect(jsonPath("$.name").value("Война и мир"))
                .andExpect(jsonPath("$.author").value("Лев Николаевич Толстой"))
                .andExpect(jsonPath("$.page").value(1408))
                .andExpect(jsonPath("$.weightInGrams").value(1020))
                .andExpect(jsonPath("$.priceInKopecks").value(2000))
                .andExpect(status().isOk());

        verify(bookService).getBookDtoById(Mockito.eq(1L));
    }

    @Test
    public void testGetAllBookDtos() throws Exception {

        List<BookDto> bookDtosTest = getBookDtosForTest();

        when(bookService.getAllBookDtos()).thenReturn(bookDtosTest);

        mockMvc.perform(get("/book/get-all"))
                .andExpect(jsonPath("$.[0].id").value(1L))
                .andExpect(jsonPath("$.[0].isbn").value("978-5-389-07123-0"))
                .andExpect(jsonPath("$.[0].name").value("Война и мир"))
                .andExpect(jsonPath("$.[0].author").value("Лев Николаевич Толстой"))
                .andExpect(jsonPath("$.[0].page").value(1408))
                .andExpect(jsonPath("$.[0].weightInGrams").value(1020))
                .andExpect(jsonPath("$.[0].priceInKopecks").value(2000))
                .andExpect(jsonPath("$.[1].id").value(2L))
                .andExpect(jsonPath("$.[1].isbn").value("978-5-389-04935-2"))
                .andExpect(jsonPath("$.[1].name").value("Анна Каренина"))
                .andExpect(jsonPath("$.[1].author").value("Лев Николаевич Толстой"))
                .andExpect(jsonPath("$.[1].page").value(864))
                .andExpect(jsonPath("$.[1].weightInGrams").value(571))
                .andExpect(jsonPath("$.[1].priceInKopecks").value(1150))
                .andExpect(status().isOk());

        verify(bookService).getAllBookDtos();
    }

    @Test
    public void testSaveBookDto() throws Exception {

        BookDto bookDtoTest = getBookDtoForTest();

        when(bookService.saveBookDto(any(BookDto.class))).thenReturn(bookDtoTest);

        mockMvc.perform(post("/book/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\": 1," +
                        "\"isbn\":\"978-5-389-07123-0\"," +
                        "\"name\":\"Война и мир\"," +
                        "\"author\":\"Лев Николаевич Толстой\"," +
                        "\"page\":1408," +
                        "\"weightInGrams\":1020," +
                        "\"priceInKopecks\":2000}"))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.isbn").value("978-5-389-07123-0"))
                .andExpect(jsonPath("$.name").value("Война и мир"))
                .andExpect(jsonPath("$.author").value("Лев Николаевич Толстой"))
                .andExpect(jsonPath("$.page").value("1408"))
                .andExpect(jsonPath("$.weightInGrams").value(1020))
                .andExpect(jsonPath("$.priceInKopecks").value(2000))
                .andExpect(status().isCreated());

        verify(bookService).saveBookDto(any(BookDto.class));
    }

    @Test
    public void testUpdateBookDto() throws Exception {

        BookDto bookDtoTest = getBookDtoForTest();

        when(bookService.updateBookDto(any(BookDto.class))).thenReturn(bookDtoTest);

        mockMvc.perform(put("/book/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\": 1," +
                        "\"isbn\":\"978-5-389-07123-0\"," +
                        "\"name\":\"Война и мир\"," +
                        "\"author\":\"Лев Николаевич Толстой\"," +
                        "\"page\":1408," +
                        "\"weightInGrams\":1020," +
                        "\"priceInKopecks\":2000}"))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.isbn").value("978-5-389-07123-0"))
                .andExpect(jsonPath("$.name").value("Война и мир"))
                .andExpect(jsonPath("$.author").value("Лев Николаевич Толстой"))
                .andExpect(jsonPath("$.page").value("1408"))
                .andExpect(jsonPath("$.weightInGrams").value(1020))
                .andExpect(jsonPath("$.priceInKopecks").value(2000))
                .andExpect(status().isOk());

        verify(bookService).updateBookDto(any(BookDto.class));
    }

    @Test
    public void testDeleteBook() throws Exception {

        when(bookService.deleteBook(anyLong())).thenReturn(true);

        mockMvc.perform(delete("/book/delete")
                .contentType(MediaType.APPLICATION_JSON)
                .content("1"))
                .andExpect(status().isOk());

        verify(bookService).deleteBook(anyLong());
    }

    @Test
    public void testStatusForbiddenBookDtoSave() throws Exception {

        mockMvc.perform(post("/book/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isForbidden());
    }

    @Test
    public void testStatusForbiddenBookDtoUpdate() throws Exception {

        mockMvc.perform(post("/book/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isForbidden());
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

}
