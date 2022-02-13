package com.example.task04app.mapper.rowmapper;

import com.example.task04app.model.Book;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

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


