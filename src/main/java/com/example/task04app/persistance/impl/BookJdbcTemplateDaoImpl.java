package com.example.task04app.persistance.impl;

import com.example.task04app.mapper.BookRowMapper;
import com.example.task04app.model.Book;
import com.example.task04app.persistance.BookDao;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import static com.example.task04app.persistance.query.BookSqlQuery.*;


@Slf4j
@AllArgsConstructor
@Repository
public class BookJdbcTemplateDaoImpl implements BookDao {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    private final BookRowMapper bookRowMapper;

    private final KeyHolder keyHolder;

    @Override
    public List<Book> getAll() throws SQLException {

        return jdbcTemplate.query(SELECT_BOOKS_SQL_QUERY, bookRowMapper);
    }

    @Override
    public Book getById(Long id) throws SQLException {

        SqlParameterSource param = new MapSqlParameterSource("id", id);

        Book book = jdbcTemplate.queryForObject(SELECT_BOOK_BY_ID_JDBC_TEMPLATE_SQL_QUERY,
                param,
                bookRowMapper);

        return book;
    }

    @Override
    public List<Book> getByAuthor(String author) throws SQLException {

        List<Book> bookList;
        SqlParameterSource param = new MapSqlParameterSource("author", "%" + author + "%");

        bookList = jdbcTemplate.query(SELECT_BOOK_BY_AUTHOR_JDBC_TEMPLATE_SQL_QUERY,
                param,
                bookRowMapper);

        return bookList;
    }

    @Override
    public boolean delete(Long id) throws SQLException {

        int countOfChanges = jdbcTemplate.update(DELETE_BOOK_BY_ID_JDBC_TEMPLATE_SQL_QUERY,
                new MapSqlParameterSource("id", id));

        if (countOfChanges == 0) {
            return false;
        }

        return true;
    }

    @Override
    public Book update(Book book) throws SQLException {

        MapSqlParameterSource params = new MapSqlParameterSource();
        setToMapSqlParameterSource(book, params);
        params.addValue("id", book.getId());

        jdbcTemplate.update(UPDATE_BOOK_JDBC_TEMPLATE_SQL_QUERY,
                params,
                keyHolder);

        Map<String, Object> keyHolderMap = keyHolder.getKeys();

        if (keyHolderMap == null) {
            return null;
        }

        Book bookUpdated = convertKeyHolderMapToBook(keyHolderMap);

        return bookUpdated;
    }

    @Override
    public Book save(Book book) throws SQLException {

        MapSqlParameterSource params = new MapSqlParameterSource();
        setToMapSqlParameterSource(book, params);

        jdbcTemplate.update(INSERT_BOOK_JDBC_TEMPLATE_SQL_QUERY, params, keyHolder);

        Map<String, Object> keyHolderMap = keyHolder.getKeys();

        if (keyHolderMap == null) {
            return null;
        }

        Book bookSaved = convertKeyHolderMapToBook(keyHolderMap);

        return bookSaved;
    }

    private void setToMapSqlParameterSource(Book book, MapSqlParameterSource params) {

        params.addValue("isbn", book.getIsbn());
        params.addValue("name", book.getName());
        params.addValue("author", book.getAuthor());
        params.addValue("page", book.getPage());
        params.addValue("weightingrams", book.getWeightInGrams());
        params.addValue("priceinkopecks", book.getPriceInKopecks());
    }

    private Book convertKeyHolderMapToBook(Map<String, Object> keyHolderMap) {

        return Book.builder()
                .id((Long) keyHolderMap.get("id"))
                .isbn((String) keyHolderMap.get("isbn"))
                .name((String) keyHolderMap.get("name"))
                .author((String) keyHolderMap.get("author"))
                .page((Integer) keyHolderMap.get("page"))
                .weightInGrams((Integer) keyHolderMap.get("weightingrams"))
                .priceInKopecks((Integer) keyHolderMap.get("priceinkopecks"))
                .build();
    }
}
