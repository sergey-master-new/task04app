package com.example.task04app.persistance.dao.impl;

import com.example.task04app.mapper.rowmapper.BookRowMapper;
import com.example.task04app.model.Book;
import com.example.task04app.persistance.impl.BookJdbcTemplateDaoImpl;
import com.example.task04app.persistance.query.BookSqlQuery;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class BookJdbcTemplateDaoImplTest {

    @Mock
    NamedParameterJdbcTemplate jdbcTemplate;

    @Mock
    BookRowMapper bookRowMapper;

    @Mock
    KeyHolder keyHolder;

    @InjectMocks
    BookJdbcTemplateDaoImpl bookJdbcTemplateDaoImpl;

    @Test
    void testGetAll() throws SQLException {

        List<Book> booksTest = getBooksForTest();

        when(jdbcTemplate.query(BookSqlQuery.SELECT_BOOKS_SQL_QUERY, bookRowMapper)).thenReturn(booksTest);

        List<Book> result = bookJdbcTemplateDaoImpl.getAll();

        Assertions.assertEquals(Arrays.<Book>asList(getBookForTest(), getBook2ForTest()), result);

        verify(jdbcTemplate).query(BookSqlQuery.SELECT_BOOKS_SQL_QUERY, bookRowMapper);
    }

    @Test
    void testGetById() throws SQLException {

        Book bookTest = getBookForTest();

        when(jdbcTemplate.queryForObject(any(), any(SqlParameterSource.class), any(BookRowMapper.class)))
                .thenReturn(bookTest);

        Book result = bookJdbcTemplateDaoImpl.getById(1L);

        Assert.assertEquals(getBookForTest(), result);

        verify(jdbcTemplate).queryForObject(any(), any(SqlParameterSource.class), any(RowMapper.class));
    }

    @Test
    void testDelete() throws SQLException {

        Long bookIdTest = 1l;

        bookJdbcTemplateDaoImpl.delete(bookIdTest);

        verify(jdbcTemplate).update(anyString(), any(MapSqlParameterSource.class));
    }

    @Test
    @Ignore
    void testUpdate() throws SQLException {

        Book bookTest = getBookForTest();

        when(keyHolder.getKeys()).thenReturn(getKeyHolderMap());

        when(jdbcTemplate.update(anyString(), any(MapSqlParameterSource.class), any(KeyHolder.class)))
                .thenReturn(1);

        Book result = bookJdbcTemplateDaoImpl.update(bookTest);

        assertEquals(bookTest, result);

        verify(jdbcTemplate).update(anyString(), any(MapSqlParameterSource.class), any(KeyHolder.class));
    }

    @Test
    void testSave() throws SQLException {

        Book bookTest = getBookForTest();

        when(keyHolder.getKeys()).thenReturn(getKeyHolderMap());

        when(jdbcTemplate.update(anyString(), any(MapSqlParameterSource.class), any(KeyHolder.class)))
                .thenReturn(1);

        Book result = bookJdbcTemplateDaoImpl.save(bookTest);

        assertEquals(bookTest, result);

        verify(jdbcTemplate).update(anyString(), any(MapSqlParameterSource.class), any(KeyHolder.class));
    }

    private List<Book> getBooksForTest() {

        Book bookTest = getBookForTest();
        Book bookTest2 = getBook2ForTest();
        List<Book> books = Arrays.asList(bookTest, bookTest2);

        return books;
    }

    private Book getBookForTest() {

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

    private Book getBook2ForTest() {

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

    private Map<String, Object> getKeyHolderMap() {
        Map<String, Object> keyHolderMap = new HashMap<>();
        keyHolderMap.put("id", 1L);
        keyHolderMap.put("isbn", "978-5-389-07123-0");
        keyHolderMap.put("name", "Война и мир");
        keyHolderMap.put("author", "Лев Николаевич Толстой");
        keyHolderMap.put("page", 1408);
        keyHolderMap.put("weightingrams", 1020);
        keyHolderMap.put("priceinkopecks", 2000);
        return keyHolderMap;
    }
}