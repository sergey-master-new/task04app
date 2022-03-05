/*
 * @author S.Maevsky
 * @since 04.03.2022, 16:15
 * @version V 1.0.0
 */

package com.example.task04app.mapper;

import com.example.task04app.entity.Book;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The type Book row mapper.
 */
@Component
public class BookRowMapper implements RowMapper<Book> {

    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {

        Book book = new Book();
        book.setId(rs.getLong("id"));
        book.setIsbn(rs.getString("isbn"));
        book.setName(rs.getString("name"));
        book.setAuthor(rs.getString("author"));
        book.setPage(rs.getInt("page"));
        book.setWeightInGrams(rs.getInt("weightingrams"));
        book.setPriceInKopecks(rs.getInt("priceinkopecks"));

        return book;
    }
}


