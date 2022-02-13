package com.example.task04app.persistance;

import com.example.task04app.model.Book;

import java.sql.SQLException;
import java.util.List;


public interface BookDao extends Dao<Book> {

    List<Book> getByAuthor(String author) throws SQLException;

}
