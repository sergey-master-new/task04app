package com.example.task04app.persistance.dao.impl;

import com.example.task04app.mapper.rowmapper.BookRowMapper;
import com.example.task04app.model.Book;
import com.example.task04app.persistance.dao.BookDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;
import static com.example.task04app.persistance.dao.query.SqlQuery.*;

@Slf4j
@Repository
public class BookJdbcTemplateDaoImpl implements BookDao {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    private BookRowMapper bookRowMapper;

    @Override
    public List<Book> getAll() {

        return jdbcTemplate.query(SELECT_BOOKS_SQL_QUERY,
                bookRowMapper);
    }

    @Override
    public Book getById(Long id) {

        SqlParameterSource param = new MapSqlParameterSource("id", id);

        Book book = jdbcTemplate.queryForObject(SELECT_BOOK_BY_ID_JDBC_TEMPLATE_SQL_QUERY,
                param,
                bookRowMapper);

        return book;
    }

    @Override
    public void delete(Long id) {

        jdbcTemplate.update(DELETE_BOOK_BY_ID_JDBC_TEMPLATE_SQL_QUERY,
                new MapSqlParameterSource("id", id));
    }

    @Override
    public Book update(Book book) {

        MapSqlParameterSource params = new MapSqlParameterSource();

        toMapSqlParameterSource(book, params);
        params.addValue("id", book.getId());

        jdbcTemplate.update(UPDATE_BOOK_JDBC_TEMPLATE_SQL_QUERY,
                params);

        return getById(book.getId());
    }

    @Override
    public Book save(Book book) {

        MapSqlParameterSource params = new MapSqlParameterSource();

        toMapSqlParameterSource(book, params);

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(INSERT_BOOK_JDBC_TEMPLATE_SQL_QUERY,
                params,
                keyHolder);

        List<Map<String, Object>> keyList = keyHolder.getKeyList();

        Long idGenerated = (Long) keyList.get(0).get("id");

        Book bookSaved = getById(idGenerated);

        return bookSaved;
    }

    private void toMapSqlParameterSource(Book book, MapSqlParameterSource params) {

        params.addValue("isbn", book.getIsbn());
        params.addValue("name", book.getName());
        params.addValue("author", book.getAuthor());
        params.addValue("page", book.getPage());
        params.addValue("weightingrams", book.getWeightInGrams());
        params.addValue("priceinkopecks", book.getPriceInKopecks());
    }
}
