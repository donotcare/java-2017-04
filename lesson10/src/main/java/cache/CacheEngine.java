package cache;

import service.message.Address;
import service.message.Addressee;

import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

public class CacheEngine<K, V> implements Cache<K, V>, Addressee {
    private static final int TIME_THRESHOLD_MS = 5;

    private final CacheSettings settings;
    private final Address address;

    private final Map<K, CacheElement<K, V>> elements = new ConcurrentHashMap<>();
    private final Timer timer = new Timer();

    private int hit = 0;
    private int miss = 0;

    public CacheEngine(Address address, CacheSettings settings) {
        this.address = address;
        this.settings = settings;
    }

    @Override
    public void put(CacheElement<K, V> element) {
        if (elements.size() == settings.getMaxElements()) {
            K key = elements.keySet().iterator().next();
            elements.remove(key);
        }

        K key = element.getKey();
        elements.put(key, element);

        if (!settings.isEternal()) {
            if (settings.getLifeTimeMs() != 0) {
                TimerTask lifeTimerTask = getTimerTask(key, lifeElement -> lifeElement.getCreationTime() + settings.getLifeTimeMs());
                timer.schedule(lifeTimerTask, settings.getLifeTimeMs());
            }
            if (settings.getIdleTimeMs() != 0) {
                TimerTask idleTimerTask = getTimerTask(key, idleElement -> idleElement.getLastAccessTime() + settings.getIdleTimeMs());
                timer.schedule(idleTimerTask, settings.getIdleTimeMs());
            }
        }
    }

    @Override
    public V get(K key) {
        CacheElement<K, V> element = elements.get(key);
        if (element != null && element.getValue() != null) {
            hit++;
            element.setAccessed();
            return element.getValue();
        } else {
            miss++;
            return null;
        }
    }

    @Override
    public int getHitCount() {
        return hit;
    }

    @Override
    public int getMissCount() {
        return miss;
    }

    @Override
    public void dispose() {
        timer.cancel();
    }

    private TimerTask getTimerTask(final K key, Function<CacheElement<K, V>, Long> timeFunction) {
        return new TimerTask() {
            @Override
            public void run() {
                CacheElement<K, V> checkedElement = elements.get(key);
                if (checkedElement == null ||
                        isT1BeforeT2(timeFunction.apply(checkedElement), checkedElement.getCurrentTime())) {
                    elements.remove(key);
                }
            }
        };
    }

    public void clear() {
        elements.clear();
    }

    public int getCachedElementsCount() {
        return elements.size();
    }

    public CacheSettings getSettings() {
        return settings;
    }

    private boolean isT1BeforeT2(long t1, long t2) {
        return t1 < t2 + TIME_THRESHOLD_MS;
    }

    @Override
    public Address getAddress() {
        return address;
    }
}
