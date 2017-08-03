package ru.otus.l16.backend.cache;

public class CacheSettings {
    private int maxElements;
    private long lifeTimeMs;
    private long idleTimeMs;
    private boolean isEternal;

    public CacheSettings(int maxElements, long lifeTimeMs, long idleTimeMs, boolean isEternal) {
        this.maxElements = maxElements;
        this.lifeTimeMs = lifeTimeMs;
        this.idleTimeMs = idleTimeMs;
        this.isEternal = isEternal;
    }

    public int getMaxElements() {
        return maxElements;
    }

    public long getLifeTimeMs() {
        return lifeTimeMs;
    }

    public long getIdleTimeMs() {
        return idleTimeMs;
    }

    public boolean isEternal() {
        return isEternal;
    }
}
