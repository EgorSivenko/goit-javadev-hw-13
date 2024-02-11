package org.example.dao;

import java.util.List;

public interface DAO<T, ID> {

    T findById(ID id);

    List<T> findAll();

    void save(T t);

    void update(T t);

    void delete(T t);

    T deleteById(ID id);
}
