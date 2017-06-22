package cache;

public interface Cache<K, V> {
    void put(CacheElement<K, V> element);

    V get(K key);

    int getHitCount();

    int getMissCount();

    void dispose();
}
