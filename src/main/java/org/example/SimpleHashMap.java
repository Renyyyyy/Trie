package org.example;

import java.util.*;

public class SimpleHashMap<K, V> {
    private static final int DEFAULT_CAPACITY = 16;
    private LinkedList<Entry<K, V>>[] table;
    private int size;

    public int getSize() {
        return size;
    }

    private static class Entry<K, V> {
        K key;
        V value;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public SimpleHashMap() {
        table = new LinkedList[DEFAULT_CAPACITY];
        size = 0;
    }

    public void put(K key, V value) {
        if (key == null)
            throw new IllegalArgumentException("Key cannot be null");

        int index = getIndex(key);
        if (table[index] == null) {
            table[index] = new LinkedList<>();
        }


        for (Entry<K, V> entry : table[index]) {
            if (entry.key.equals(key)) {
                entry.value = value;
                return;
            }
        }

        table[index].add(new Entry<>(key, value));
        size++;

        if (size > table.length * 0.75) {
            resize();
        }
    }

    public V computeIfAbsent(K key, java.util.function.Function<? super K, ? extends V> mappingFunction) {
        if (key == null)
            throw new IllegalArgumentException("Key cannot be null");

        int index = getIndex(key);
        if (table[index] == null) {
            table[index] = new LinkedList<>();
        }

        for (Entry<K, V> entry : table[index]) {
            if (entry.key.equals(key)) {
                return entry.value;
            }
        }

        V value = mappingFunction.apply(key);
        table[index].add(new Entry<>(key, value));
        size++;

        if (size > table.length * 0.75) {
            resize();
        }

        return value;
    }

    public V get(K key) {
        int index = getIndex(key);
        if (table[index] != null) {
            for (Entry<K, V> entry : table[index]) {
                if (entry.key.equals(key)) {
                    return entry.value;
                }
            }
        }
        return null;
    }

    public Set<K> keySet() {
        Set<K> keySet = new HashSet<>();
        for (LinkedList<Entry<K, V>> list : table) {
            if (list != null) {
                for (Entry<K, V> entry : list) {
                    keySet.add(entry.key);
                }
            }
        }
        return keySet;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void remove(K key) {
        int index = getIndex(key);
        if (table[index] != null) {
            table[index].removeIf(entry -> entry.key.equals(key));
            size--;
        }
    }

    public Collection<V> values() {
        List<V> values = new ArrayList<>();
        for (LinkedList<Entry<K, V>> list : table) {
            if (list != null) {
                for (Entry<K, V> entry : list) {
                    values.add(entry.value);
                }
            }
        }
        return values;
    }

    private int getIndex(K key) {
        return Math.abs(key.hashCode()) % table.length;
    }

    private void resize() {
        LinkedList<Entry<K, V>>[] newTable = new LinkedList[table.length * 2];
        for (LinkedList<Entry<K, V>> list : table) {
            if (list != null) {
                for (Entry<K, V> entry : list) {
                    int newIndex = Math.abs(entry.key.hashCode()) % newTable.length;
                    if (newTable[newIndex] == null) {
                        newTable[newIndex] = new LinkedList<>();
                    }
                    newTable[newIndex].add(entry);
                }
            }
        }
        table = newTable;
    }
}

