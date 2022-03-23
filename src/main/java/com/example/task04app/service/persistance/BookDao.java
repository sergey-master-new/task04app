/*
 * @author S.Maevsky
 * @since 04.03.2022, 16:17
 * @version V 1.0.0
 */

package com.example.task04app.service.persistance;

import com.example.task04app.persistance.entity.Book;
import org.springframework.dao.DataAccessException;

import java.util.List;


/**
 * The interface Book dao.
 */
public interface BookDao {

    /**
     * Gets all.
     *
     * @return the all
     * @throws DataAccessException the data access exception
     */
    List<Book> getAll() throws DataAccessException;

    /**
     * Gets by id.
     *
     * @param id the id
     * @return the by id
     * @throws DataAccessException the data access exception
     */
    Book getById (Long id) throws DataAccessException;

    /**
     * Save e.
     *
     * @param entity the entity
     * @return the e
     * @throws DataAccessException the data access exception
     */
    Book save(Book entity) throws DataAccessException;

    /**
     * Update e.
     *
     * @param entity the entity
     * @return the e
     * @throws DataAccessException the data access exception
     */
    Book update(Book entity) throws DataAccessException;

    /**
     * Delete boolean.
     *
     * @param id the id
     * @return the boolean
     * @throws DataAccessException the data access exception
     */
    boolean delete(Long id) throws DataAccessException;

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
