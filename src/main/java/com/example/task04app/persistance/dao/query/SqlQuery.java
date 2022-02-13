package com.example.task04app.persistance.dao.query;

public final class SqlQuery {

    private SqlQuery() {
    }

    public static final String SELECT_BOOKS_SQL_QUERY = "SELECT * FROM books";

    public static final String SELECT_BOOK_BY_ID_SQL_QUERY =
            "SELECT isbn, name, author, page, weightingrams, priceinkopecks FROM books WHERE id = ?";

    public static final String INSERT_BOOK_SQL_QUERY =
            "INSERT INTO books (isbn, name, author, page, weightingrams, priceinkopecks) VALUES (?,?,?,?,?,?)";

    public static final String UPDATE_BOOK_SQL_QUERY =
            "UPDATE books SET isbn = ?, name = ?, author = ?, page = ?, weightingrams = ?, " +
                    "priceinkopecks = ? WHERE id = ?";

    public static final String DELETE_BOOK_BY_ID_SQL_QUERY = "DELETE FROM books WHERE id = ?";

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
