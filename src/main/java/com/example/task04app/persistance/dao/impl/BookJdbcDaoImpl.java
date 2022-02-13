package com.example.task04app.persistance.dao.impl;

import com.example.task04app.exception.DataBaseSQLException;
import com.example.task04app.model.Book;
import com.example.task04app.persistance.connector.Connector;
import com.example.task04app.persistance.dao.BookDao;
import com.example.task04app.persistance.dao.query.SqlQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class BookJdbcDaoImpl implements BookDao{

    private final Connector connector;

    @Override
    public List<Book> getAll() {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Book> bookList = new ArrayList<>();

        try {
            conn = connector.getConnection();
            stmt = conn.prepareStatement(SqlQuery.SELECT_BOOKS_SQL_QUERY);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Book book = Book.builder()
                        .id(rs.getLong("id"))
                        .isbn(rs.getString("isbn"))
                        .name(rs.getString("name"))
                        .author(rs.getString("author"))
                        .page(rs.getInt("page"))
                        .weightInGrams(rs.getInt("weightingrams"))
                        .priceInKopecks(rs.getInt("priceinkopecks"))
                        .build();
                bookList.add(book);
            }

        } catch (SQLException e) {
            log.info("Exception handling get all books {}", e.getMessage());
            throw new DataBaseSQLException("Exception handling get all books");

        } finally {
            connector.closeConnection(conn);
            connector.closePreparedStatement(stmt);
            connector.closeResultSet(rs);
        }
        return bookList;
    }

    @Override
    public Book getById(Long id) {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = connector.getConnection();
            stmt = conn.prepareStatement(SqlQuery.SELECT_BOOK_BY_ID_SQL_QUERY);
            stmt.setLong(1, id);
            rs = stmt.executeQuery();

            if (rs.next()) {
                return Book.builder()
                        .id(id)
                        .isbn(rs.getString("isbn"))
                        .name(rs.getString("name"))
                        .author(rs.getString("author"))
                        .page(rs.getInt("page"))
                        .weightInGrams(rs.getInt("weightingrams"))
                        .priceInKopecks(rs.getInt("priceinkopecks"))
                        .build();
            }

            return null;

        } catch (SQLException e) {
            log.info("Exception when handling  get book by id", e.getMessage());
            throw new DataBaseSQLException("Exception when handling get book by id");

        } finally {
            connector.closeConnection(conn);
            connector.closePreparedStatement(stmt);
            connector.closeResultSet(rs);
        }
    }

    @Override
    public Book save(Book book) {

        Connection conn = null;
        PreparedStatement stmt = null;

        try {

            conn = connector.getConnection();
            stmt = conn.prepareStatement(SqlQuery.INSERT_BOOK_SQL_QUERY, Statement.RETURN_GENERATED_KEYS);
            setParametersToPrepareStatement(book, stmt);
            stmt.executeUpdate();

            getID(book, stmt);

            return getById(book.getId());

        } catch (SQLException e) {

            log.info("Exception handling save book", e.getMessage());

            throw new DataBaseSQLException("Exception handling save book");

        } finally {

            connector.closeConnection(conn);
            connector.closePreparedStatement(stmt);
        }
    }

    @Override
    public Book update(Book book) {

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = connector.getConnection();
            stmt = conn.prepareStatement(SqlQuery.UPDATE_BOOK_SQL_QUERY);
            setParametersToPrepareStatement(book, stmt);
            stmt.setLong(7, book.getId());

            stmt.execute();

            Book bookUpdated = getById(book.getId());

            return bookUpdated;
        } catch (SQLException e) {
            log.info("Exception when handling update book", e.getMessage());
            throw new DataBaseSQLException("Exception when handling update book");

        } finally {
            connector.closeConnection(conn);
            connector.closePreparedStatement(stmt);
        }
    }

    @Override
    public void delete(Long id) {

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = connector.getConnection();
            stmt = conn.prepareStatement(SqlQuery.DELETE_BOOK_BY_ID_SQL_QUERY);
            stmt.setLong(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            log.info("Exception handling delete book", e.getMessage());
            throw new DataBaseSQLException("Exception handling get delete book");

        } finally {
            connector.closeConnection(conn);
            connector.closePreparedStatement(stmt);
        }
    }

    private void setParametersToPrepareStatement(Book book, PreparedStatement stmt) throws SQLException {
        stmt.setString(1, book.getIsbn());
        stmt.setString(2, book.getName());
        stmt.setString(3, book.getAuthor());
        stmt.setInt(4, book.getPage());
        stmt.setInt(5, book.getWeightInGrams());
        stmt.setInt(6, book.getPriceInKopecks());
    }

    private void getID(Book book, PreparedStatement stmt) throws SQLException {

        try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {

            if (generatedKeys.next()) {
                book.setId(generatedKeys.getLong(1));
            } else {
                throw new DataBaseSQLException("Creating book failed");
            }
        }
    }
}
