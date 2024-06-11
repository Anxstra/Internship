package com.anxstra;

import java.util.Optional;

public interface Cache<K, V> {

    void put(K key, V value);

    Optional<V> get(K key);

    void remove(K key);

    boolean contains(K key);

    int size();

    boolean isEmpty();

    void clear();
}
