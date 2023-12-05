package ru.kuzmin.apicatalog.cache;

import lombok.Data;

@Data
public class CacheSettings {
    private Integer refreshAfterWriteTime;
    private Integer expireAfterWriteTime;
    private Integer maxSize;
    private static final Integer DEFAULT_REFRESH_AFTER = 10;
    private static final Integer DEFAULT_EXPIRE_AFTER = 15;
    private static final Integer DEFAULT_MAX_SIZE = 180;
    public static final CacheSettings DEFAULT_CACHE_SETTINGS = new
            CacheSettings(DEFAULT_REFRESH_AFTER,
            DEFAULT_EXPIRE_AFTER, DEFAULT_MAX_SIZE);

    public CacheSettings() {}

    public CacheSettings(Integer refreshAfterWriteTime, Integer
            expireAfterWriteTime, Integer maxSize) {
        this.refreshAfterWriteTime = refreshAfterWriteTime;
        this.expireAfterWriteTime = expireAfterWriteTime;
        this.maxSize = maxSize;
    }
}
