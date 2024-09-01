package br.com.nt.kafkacachesync.application.service.impl;

import br.com.nt.kafkacachesync.domain.model.Sale;
import br.com.nt.kafkacachesync.domain.service.CacheService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class CacheServiceImpl implements CacheService {

    private final RedisTemplate<String, Object> redisTemplate;

    private static final String SALE_CACHE_PREFIX = "sale_";

    @Override
    public boolean isSaleCached(String uniqueKey) {
        return Boolean.TRUE.equals(redisTemplate.hasKey(SALE_CACHE_PREFIX + uniqueKey));
    }

    @Override
    public void cacheSale(String uniqueKey, Sale sale) {
        redisTemplate.opsForValue().set(SALE_CACHE_PREFIX + uniqueKey, sale, Duration.ofHours(1));
    }
}
