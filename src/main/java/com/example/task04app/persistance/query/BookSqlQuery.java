package com.example.task04app.persistance.query;

public final class BookSqlQuery {

    public BookSqlQuery() {
    }

    public static final String SELECT_BOOKS_SQL_QUERY = "SELECT * FROM books ORDER BY id ASC";

    public static final String SELECT_BOOK_BY_ID_JDBC_TEMPLATE_SQL_QUERY =
            "SELECT * FROM books WHERE id = :id";

    public static final String DELETE_BOOK_BY_ID_JDBC_TEMPLATE_SQL_QUERY = "DELETE FROM books WHERE id = :id";

    public static final String UPDATE_BOOK_JDBC_TEMPLATE_SQL_QUERY =
            "UPDATE books SET isbn = :isbn, name = :name, author = :author, page = :page, " +
                    "weightingrams = :weightingrams, priceinkopecks = :priceinkopecks WHERE id = :id";

    public static final String INSERT_BOOK_JDBC_TEMPLATE_SQL_QUERY =
            "INSERT INTO books (isbn, name, author, weightingrams, page, priceinkopecks) " +
                    "VALUES (:isbn, :name, :author, :weightingrams, :page,  :priceinkopecks)";

}
