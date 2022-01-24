package com.example.task04app.persistance.dao.query;

public final class SqlQuery {

    private SqlQuery() {
    }

    public static final String SELECT_BOOKS_SQL_QUERY = "SELECT * FROM books";
    public static final String SELECT_BOOK_BY_ID_SQL_QUERY =
            "SELECT isbn, name, author, pageCount, weightInGrams, priceInKopecks FROM books WHERE id = ?";
    public static final String INSERT_BOOK_SQL_QUERY =
            "INSERT INTO books (isbn, name, author, pageCount, weightInGrams, priceInKopecks) VALUES (?,?,?,?,?,?)";
    public static final String UPDATE_BOOK_SQL_QUERY =
            "UPDATE books SET isbn = ?, name = ?, author = ?, pageCount = ?, weightInGrams = ?, " +
                    "priceInKopecks = ? WHERE id = ?";
    public static final String DELETE_BOOK_BY_ID_SQL_QUERY = "DELETE FROM books WHERE id = ?";
}
