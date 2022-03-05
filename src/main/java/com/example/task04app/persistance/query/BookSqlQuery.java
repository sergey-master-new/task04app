/*
 * @author S.Maevsky
 * @since 04.03.2022, 16:17
 * @version V 1.0.0
 */

package com.example.task04app.persistance.query;

/**
 * The type Book sql query.
 */
public final class BookSqlQuery {

    private BookSqlQuery() {
    }

    /**
     * The constant SELECT_BOOKS_SQL_QUERY.
     */
    public static final String SELECT_BOOKS_SQL_QUERY = "SELECT * FROM books ORDER BY id ASC";

    /**
     * The constant SELECT_BOOK_BY_ID_JDBC_TEMPLATE_SQL_QUERY.
     */
    public static final String SELECT_BOOK_BY_ID_JDBC_TEMPLATE_SQL_QUERY =
            "SELECT * FROM books WHERE id = :id";

    /**
     * The constant SELECT_BOOK_BY_AUTHOR_JDBC_TEMPLATE_SQL_QUERY.
     */
    public static final String SELECT_BOOK_BY_AUTHOR_JDBC_TEMPLATE_SQL_QUERY =
            "SELECT * FROM books WHERE author LIKE :author";

    /**
     * The constant SELECT_BOOK_BY_TITLE_JDBC_TEMPLATE_SQL_QUERY.
     */
    public static final String SELECT_BOOK_BY_TITLE_JDBC_TEMPLATE_SQL_QUERY =
            "SELECT * FROM books WHERE name LIKE :name";

    /**
     * The constant DELETE_BOOK_BY_ID_JDBC_TEMPLATE_SQL_QUERY.
     */
    public static final String DELETE_BOOK_BY_ID_JDBC_TEMPLATE_SQL_QUERY = "DELETE FROM books WHERE id = :id";

    /**
     * The constant UPDATE_BOOK_JDBC_TEMPLATE_SQL_QUERY.
     */
    public static final String UPDATE_BOOK_JDBC_TEMPLATE_SQL_QUERY =
            "UPDATE books SET isbn = :isbn, name = :name, author = :author, page = :page, " +
                    "weightingrams = :weightingrams, priceinkopecks = :priceinkopecks WHERE id = :id";

    /**
     * The constant INSERT_BOOK_JDBC_TEMPLATE_SQL_QUERY.
     */
    public static final String INSERT_BOOK_JDBC_TEMPLATE_SQL_QUERY =
            "INSERT INTO books (isbn, name, author, weightingrams, page, priceinkopecks) " +
                    "VALUES (:isbn, :name, :author, :weightingrams, :page,  :priceinkopecks)";

}
