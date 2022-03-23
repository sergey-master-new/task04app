/*
 * @author S.Maevsky
 * @since 04.03.2022, 16:17
 * @version V 1.0.0
 */

package com.example.task04app.persistance.serviceimpl;

import com.example.task04app.persistance.entity.Book;
import com.example.task04app.mapper.BookRowMapper;
import com.example.task04app.service.persistance.BookDao;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

import static com.example.task04app.persistance.query.BookSqlQuery.*;


/**
 * The type Book jdbc template dao.
 */
@Slf4j
@AllArgsConstructor
@Repository
public class BookJdbcTemplateDaoImpl implements BookDao {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    private final BookRowMapper bookRowMapper;

    private final KeyHolder keyHolder;

    @Override
    public List<Book> getAll()  throws DataAccessException {

        return jdbcTemplate.query(SELECT_BOOKS_SQL_QUERY, bookRowMapper);
    }

    @Override
    public Book getById(Long id)  throws DataAccessException {

        SqlParameterSource param = new MapSqlParameterSource("id", id);

        return jdbcTemplate.queryForObject(SELECT_BOOK_BY_ID_JDBC_TEMPLATE_SQL_QUERY,
                param,
                bookRowMapper);
    }

    @Override
    public List<Book> getByAuthor(String author)  throws DataAccessException {

        List<Book> bookList;
        SqlParameterSource param = new MapSqlParameterSource("author", "%" + author + "%");

        bookList = jdbcTemplate.query(SELECT_BOOK_BY_AUTHOR_JDBC_TEMPLATE_SQL_QUERY,
                param,
                bookRowMapper);

        return bookList;
    }

    @Override
    public List<Book> getByTitle(String title)  throws DataAccessException {

        List<Book> bookList;
        SqlParameterSource param = new MapSqlParameterSource("name", "%" + title + "%");

        bookList = jdbcTemplate.query(SELECT_BOOK_BY_TITLE_JDBC_TEMPLATE_SQL_QUERY,
                param,
                bookRowMapper);

        return bookList;
    }

    @Override
    public boolean delete(Long id)  throws DataAccessException {

        jdbcTemplate.update(DELETE_BOOK_BY_ID_JDBC_TEMPLATE_SQL_QUERY,
                new MapSqlParameterSource("id", id));

         return true;
    }

    @Override
    public Book update(Book book)  throws DataAccessException {

        MapSqlParameterSource params = new MapSqlParameterSource();
        setToMapSqlParameterSource(book, params);
        params.addValue("id", book.getId());

        jdbcTemplate.update(UPDATE_BOOK_JDBC_TEMPLATE_SQL_QUERY,
                params,
                keyHolder);

        Map<String, Object> keyHolderMap = keyHolder.getKeys();

        if (keyHolderMap == null){ return null; }

        Book bookUpdated = convertKeyHolderMapToBook(keyHolderMap);

        return bookUpdated;
    }

    @Override
    public Book save(Book book)  throws DataAccessException {

        MapSqlParameterSource params = new MapSqlParameterSource();
        setToMapSqlParameterSource(book, params);

        jdbcTemplate.update(INSERT_BOOK_JDBC_TEMPLATE_SQL_QUERY, params, keyHolder);

        Map<String, Object> keyHolderMap = keyHolder.getKeys();

        if (keyHolderMap == null){ return null; }

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
