package br.com.demo.salesapp.kafka;

import br.com.demo.salesapp.domain.dto.SaleDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class SaleConsumerService {

    private final CacheManager cacheManager;

    @KafkaListener(topics = "sales", groupId = "sales-group")
    public void listen(SaleDTO saleDTO) {
        String saleId = saleDTO.getSaleId();
        Cache cache = cacheManager.getCache("salesCache");

        if (Objects.requireNonNull(cache).get(saleId, SaleDTO.class) == null) {
            cache.put(saleId, saleDTO);
            processSale(saleDTO);
        } else {
            log.info("Sale ID {} already processed and cached.", saleId);
        }
    }

    private void processSale(SaleDTO saleDTO) {
        log.info("Processing sale: {}", saleDTO);
    }
}
