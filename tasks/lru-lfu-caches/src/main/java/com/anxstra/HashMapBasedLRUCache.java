package com.anxstra;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Optional;

public class HashMapBasedLRUCache<K, V> implements Cache<K, V> {

    private final int capacity;
    private final Map<K, V> cache;
    private final LinkedList<K> queue;

    public HashMapBasedLRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();
        this.queue = new LinkedList<>();
    }

    @Override
    public void put(K key, V value) {
        if (cache.containsKey(key)) {
            queue.remove(key);
        } else if (cache.size() == capacity) {
            cache.remove(queue.removeFirst());
        }
        cache.put(key, value);
        queue.addLast(key);
    }

    @Override
    public Optional<V> get(K key) {
        queue.remove(key);
        queue.addLast(key);
        return Optional.ofNullable(cache.get(key));
    }

    @Override
    public void remove(K key) {
        cache.remove(key);
        queue.remove(key);
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
        queue.clear();
    }
}
