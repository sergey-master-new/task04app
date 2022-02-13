package com.example.task04app.persistance.dao;

import java.util.List;

public interface CrudDao <E>{
    List<E> getAll();

    E getById (Long id);

    E save(E entity);

    E update(E entity);

    void delete(Long id);
}
