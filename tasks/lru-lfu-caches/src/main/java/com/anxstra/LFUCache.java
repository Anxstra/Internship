package com.anxstra;


import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.PriorityQueue;

public class LFUCache<K, V> implements Cache<K, V> {

    private final int capacity;
    private final Map<K, Node<K, V>> cache;
    private final PriorityQueue<Node<K, V>> queue;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();
        this.queue = new PriorityQueue<>(Comparator.comparingInt(Node::getFrequency));
    }

    @Override
    public void put(K key, V value) {
        if (cache.containsKey(key)) {
            Node<K, V> node = cache.get(key);
            node.setValue(value);
            node.setFrequency(node.getFrequency() + 1);
        } else {
            if (cache.size() == capacity) {
                Node<K, V> node = queue.poll();
                cache.remove(node.getKey());
            }
            Node<K, V> node = new Node<>(key, value, 1);
            cache.put(key, node);
            queue.add(node);
        }
    }

    @Override
    public Optional<V> get(K key) {
        if (cache.containsKey(key)) {
            Node<K, V> node = cache.get(key);
            node.setFrequency(node.getFrequency() + 1);
        }
        return Optional.ofNullable(cache.get(key) == null ? null : cache.get(key).getValue());
    }

    @Override
    public void remove(K key) {
        Node<K, V> node = cache.remove(key);
        if (node != null) {
            queue.remove(node);
        }
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
