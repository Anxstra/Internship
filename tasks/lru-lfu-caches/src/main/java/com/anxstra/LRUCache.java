package com.anxstra;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

public class LRUCache<K, V> implements Cache<K, V> {

    private final Map<K, V> cache;

    public LRUCache(int capacity) {
        this.cache = new LinkedHashMap<>(8, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
                return size() > capacity;
            }
        };
    }

    @Override
    public void put(K key, V value) {
        cache.put(key, value);
    }

    @Override
    public Optional<V> get(K key) {
        return Optional.ofNullable(cache.get(key));
    }

    @Override
    public void remove(K key) {
        cache.remove(key);
    }

    @Override
    public boolean contains(K key) {
        return cache.containsKey(key);
    }

    @Override
    public int size() {
        return cache.size();
    }

    @Override
    public boolean isEmpty() {
        return cache.isEmpty();
    }

    @Override
    public void clear() {
        cache.clear();
    }
}
