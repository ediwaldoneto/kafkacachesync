package br.com.demo.salesapp.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Slf4j
@Configuration
@EnableCaching
public class CacheConfig {

    @Value("${cache.expireAfterWrite:10}")
    private int expireAfterWrite;

    @Value("${cache.maximumSize:1000}")
    private int maximumSize;

    @Bean
    public CacheManager cacheManager() {
        log.info("Configuring Caffeine Cache with expireAfterWrite= {} minutes and maximumSize= {}", expireAfterWrite, maximumSize);
        CaffeineCacheManager cacheManager = new CaffeineCacheManager();
        cacheManager.setCaffeine(Caffeine.newBuilder()
                .expireAfterWrite(expireAfterWrite, TimeUnit.MINUTES)
                .maximumSize(maximumSize));
        return cacheManager;
    }
}
