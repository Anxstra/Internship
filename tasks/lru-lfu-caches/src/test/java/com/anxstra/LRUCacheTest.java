package com.anxstra;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("LRU cache tests")
class LRUCacheTest {

    @Test
    @DisplayName("put new key")
    void testPutNew() {
        Cache<Integer, String> cache = new LRUCache<>(3);
        cache.put(1, "one");
        cache.put(2, "two");
        cache.put(3, "three");

        cache.get(1);
        cache.get(2);
        cache.get(3);
        cache.get(1);
        cache.put(4, "four");

        assertEquals(3, cache.size());
        assertEquals(Optional.empty() , cache.get(2));
        assertEquals(Optional.of("four") , cache.get(4));
    }

    @Test
    @DisplayName("put existing key")
    void testPutExisting() {
        Cache<Integer, String> cache = new LRUCache<>(3);
        cache.put(1, "one");
        cache.put(2, "two");
        cache.put(3, "three");

        cache.get(1);
        cache.get(2);
        cache.get(3);
        cache.get(1);
        cache.put(3, "new value");

        assertEquals(3, cache.size());
        assertEquals(Optional.of("one") , cache.get(1));
        assertEquals(Optional.of("new value") , cache.get(3));
    }

    @Test
    @DisplayName("get")
    void testGet() {
        Cache<Integer, String> cache = new LRUCache<>(10);
        cache.put(1, "one");
        cache.put(2, "two");
        cache.put(3, "three");

        assertEquals(Optional.of("one"), cache.get(1));
    }

    @Test
    @DisplayName("remove existing")
    void testRemoveExisting() {
        Cache<Integer, String> cache = new LRUCache<>(10);
        cache.put(1, "one");
        cache.put(2, "two");
        cache.put(3, "three");

        cache.remove(1);

        assertEquals(2, cache.size());
        assertFalse(cache.contains(1));
    }

    @Test
    @DisplayName("remove absent")
    void testRemoveAbsent() {
        Cache<Integer, String> cache = new LRUCache<>(10);
        cache.put(1, "one");
        cache.put(2, "two");
        cache.put(3, "three");

        cache.remove(4);

        assertEquals(3, cache.size());
    }

    @Test
    @DisplayName("contains")
    void testContains() {
        Cache<Integer, String> cache = new LRUCache<>(10);
        cache.put(1, "one");

        assertTrue(cache.contains(1));
    }

    @Test
    @DisplayName("size")
    void testSize() {
        Cache<Integer, String> cache = new LRUCache<>(10);
        cache.put(1, "one");
        cache.put(2, "two");
        cache.put(3, "three");

        assertEquals(3, cache.size());
    }

    @Test
    @DisplayName("is empty")
    void testIsEmpty() {
        Cache<Integer, String> cache = new LRUCache<>(10);
        cache.put(1, "one");
        cache.put(2, "two");
        cache.put(3, "three");

        assertFalse(cache.isEmpty());
    }

    @Test
    @DisplayName("clear")
    void testClear() {
        Cache<Integer, String> cache = new LRUCache<>(10);
        cache.put(1, "one");
        cache.put(2, "two");
        cache.put(3, "three");

        cache.clear();

        assertEquals(0, cache.size());
    }
}
