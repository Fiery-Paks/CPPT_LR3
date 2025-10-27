package org.example.taskOne;
import java.util.LinkedList;

public class HashTable<K, V> {

    // Внутренний класс для хранения пар ключ-значение
    private class Entry<K, V> {
        K key;
        V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private LinkedList<Entry<K, V>>[] table;
    private int size;
    private int capacity;
    private static final int DEFAULT_CAPACITY = 16; // Начальный размер внутреннего массива хэш-таблицы
    private static final double LOAD_FACTOR = 0.75; // Пороговое значение (в %) для расширения таблицы

    public HashTable() {
        this(DEFAULT_CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public HashTable(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.table = new LinkedList[capacity];

        for (int i = 0; i < capacity; i++) {
            table[i] = new LinkedList<>();
        }
    }

    private int hash(K key) {
        return Math.abs(key.hashCode()) % capacity;
    }

    public void put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }

        int index = hash(key);
        LinkedList<Entry<K, V>> bucket = table[index];

        for (Entry<K, V> entry : bucket) {
            if (entry.key.equals(key)) {
                entry.value = value;
                return;
            }
        }

        bucket.add(new Entry<>(key, value));
        size++;

        if ((double) size / capacity > LOAD_FACTOR) {
            rehash();
        }
    }

    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }

        int index = hash(key);
        LinkedList<Entry<K, V>> bucket = table[index];

        for (Entry<K, V> entry : bucket) {
            if (entry.key.equals(key)) {
                return entry.value;
            }
        }

        return null;
    }

    public boolean remove(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }

        int index = hash(key);
        LinkedList<Entry<K, V>> bucket = table[index];

        for (Entry<K, V> entry : bucket) {
            if (entry.key.equals(key)) {
                bucket.remove(entry);
                size--;
                return true;
            }
        }

        return false;
    }

    // Рехеширование при превышении коэффициента загрузки
    @SuppressWarnings("unchecked")
    private void rehash() {
        int newCapacity = capacity * 2;
        LinkedList<Entry<K, V>>[] newTable = new LinkedList[newCapacity];

        for (int i = 0; i < newCapacity; i++) {
            newTable[i] = new LinkedList<>();
        }

        for (int i = 0; i < capacity; i++) {
            for (Entry<K, V> entry : table[i]) {
                int newIndex = Math.abs(entry.key.hashCode()) % newCapacity;
                newTable[newIndex].add(entry);
            }
        }

        table = newTable;
        capacity = newCapacity;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void display() {
        for (int i = 0; i < capacity; i++) {
            System.out.print("Корзина " + i + ": ");
            for (Entry<K, V> entry : table[i]) {
                System.out.print("[" + entry.key + "=" + entry.value + "] ");
            }
            System.out.println();
        }
    }
}
