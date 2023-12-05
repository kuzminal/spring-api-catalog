package ru.kuzmin.apicatalog.cache;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.kuzmin.apicatalog.repository.CurrencyRepository;
import com.google.common.collect.Lists;

import java.util.concurrent.TimeUnit;

import java.util.function.Function;

@Configuration
@EnableCaching
public class CacheManagerConfiguration {
    public static final String CATALOG_CURRENCY = "CATALOG_CURRENCY";
    private final CacheConfiguration cacheConfiguration;
    private final CurrencyRepository currencyRepository;

    @Autowired
    public CacheManagerConfiguration(final CacheConfiguration
                                             cacheConfiguration, final CurrencyRepository currencyRepository) {
        this.cacheConfiguration = cacheConfiguration;
        this.currencyRepository = currencyRepository;
    }

    @Bean
    public CacheManager cacheManager() {
        CacheSettings cacheCitySettings = cacheConfiguration.
                getCacheSettings(CATALOG_CURRENCY);
        SimpleCacheManager simpleCacheManager = new SimpleCacheManager();
        simpleCacheManager.setCaches(Lists.newArrayList(buildCaffeineCache(CATALOG_CURRENCY,
                cacheCitySettings, currencyRepository::findById)));
        return simpleCacheManager;
    }

    public static CaffeineCache buildCaffeineCache(String cacheName,
                                                   CacheSettings settings, Function<Long, Object> serviceCall) {
        return new CaffeineCache(cacheName,
                Caffeine.newBuilder().refreshAfterWrite(settings.getRefreshAfterWriteTime(), TimeUnit.MINUTES)
                        .expireAfterWrite(settings.getExpireAfterWriteTime(), TimeUnit.MINUTES)
                        .maximumSize(settings.getMaxSize()).build(key ->
                                serviceCall.apply((Long) key)));
    }
}
