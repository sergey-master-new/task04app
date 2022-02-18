package com.example.task04app.persistance;

import java.sql.SQLException;
import java.util.List;

public interface Dao<E>{

    List<E> getAll() throws SQLException;

    E getById (Long id) throws SQLException;

    E save(E entity) throws SQLException;

    E update(E entity) throws SQLException;

    boolean delete(Long id) throws SQLException;
}
