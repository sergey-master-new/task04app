/*
 * @author S.Maevsky
 * @since 04.03.2022, 16:19
 * @version V 1.0.0
 */

package com.example.task04app.persistance;

import org.springframework.dao.DataAccessException;

import java.util.List;

/**
 * The interface Dao.
 *
 * @param <E> the type parameter
 */
public interface Dao<E>{

    /**
     * Gets all.
     *
     * @return the all
     * @throws DataAccessException the data access exception
     */
    List<E> getAll() throws DataAccessException;

    /**
     * Gets by id.
     *
     * @param id the id
     * @return the by id
     * @throws DataAccessException the data access exception
     */
    E getById (Long id) throws DataAccessException;

    /**
     * Save e.
     *
     * @param entity the entity
     * @return the e
     * @throws DataAccessException the data access exception
     */
    E save(E entity) throws DataAccessException;

    /**
     * Update e.
     *
     * @param entity the entity
     * @return the e
     * @throws DataAccessException the data access exception
     */
    E update(E entity) throws DataAccessException;

    /**
     * Delete boolean.
     *
     * @param id the id
     * @return the boolean
     * @throws DataAccessException the data access exception
     */
    boolean delete(Long id) throws DataAccessException;
}
