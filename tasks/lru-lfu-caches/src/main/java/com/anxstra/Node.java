package com.anxstra;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Node<K, V> {

    private final K key;
    private V value;
    private int frequency;
}
