/*
 * @author S.Maevsky
 * @since 04.03.2022, 16:17
 * @version V 1.0.0
 */

package com.example.task04app.persistance;

import com.example.task04app.entity.Book;
import org.springframework.dao.DataAccessException;

import java.util.List;


/**
 * The interface Book dao.
 */
public interface BookDao extends Dao<Book> {

    /**
     * Gets list of books by author.
     *
     * @param author the author
     * @return the by author
     * @throws DataAccessException the data access exception
     */
    List<Book> getByAuthor(String author) throws DataAccessException;

    /**
     * Gets list of books by title.
     *
     * @param title the title
     * @return the by title
     * @throws DataAccessException the data access exception
     */
    List<Book> getByTitle(String title) throws DataAccessException;

}
