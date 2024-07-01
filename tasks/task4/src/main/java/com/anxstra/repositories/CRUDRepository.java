package com.anxstra.repositories;

import java.util.List;
import java.util.Optional;

public interface CRUDRepository<K, T> {

    Optional<T> getById(K id);

    List<T> getAll();

    void save(T entity);

    void update(T entity);

    void deleteById(K id);
}
