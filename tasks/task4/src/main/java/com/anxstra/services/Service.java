package com.anxstra.services;

import java.util.List;

public interface Service<K, T> {

    T findById(K id);

    List<T> findAll();

    T save(T entity);

    T update(T entity);

    void delete(T entity);
}
