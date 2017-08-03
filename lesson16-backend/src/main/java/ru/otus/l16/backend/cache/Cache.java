package ru.otus.l16.backend.cache;

public interface Cache<K, V> {
    void put(CacheElement<K, V> element);

    V get(K key);

    int getHitCount();

    int getMissCount();

    int getCachedElementsCount();

    void clear();

    CacheSettings getSettings();

    void dispose();
}
