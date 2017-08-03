package ru.otus.l16.backend.cache;

import java.lang.ref.SoftReference;

public class CacheElement<K, V> {
    private final K key;
    private final SoftReference<V> value;
    private final long creationTime;
    private long lastAccessTime;


    public CacheElement(K key, V value) {
        this.key = key;
        this.value = new SoftReference<>(value);
        this.creationTime = getCurrentTime();
        this.lastAccessTime = getCurrentTime();
    }

    protected long getCurrentTime() {
        return System.currentTimeMillis();
    }

    public long getLastAccessTime() {
        return lastAccessTime;
    }

    public long getCreationTime() {
        return creationTime;
    }

    public void setAccessed() {
        lastAccessTime = getCurrentTime();
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value.get();
    }
}
