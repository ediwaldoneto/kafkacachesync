package br.com.nt.kafkacachesync.infrastructure.kafka;

import br.com.nt.kafkacachesync.application.dto.SaleDTO;
import br.com.nt.kafkacachesync.application.mapper.SaleMapper;
import br.com.nt.kafkacachesync.domain.model.Sale;
import br.com.nt.kafkacachesync.domain.service.CacheService;
import br.com.nt.kafkacachesync.domain.service.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
@RequiredArgsConstructor
public class SaleKafkaConsumer {

    private final SaleService saleService;
    private final CacheService cacheService;
    private final SaleMapper saleMapper;

    @KafkaListener(topics = "sales-topic", groupId = "sales-group")
    public void consume(SaleDTO saleDTO) {
        String uniqueKey = generateUniqueKey(saleDTO);

        if (!cacheService.isSaleCached(uniqueKey)) {
            Sale sale = SaleMapper.toEntity(saleDTO);
            saleService.saveSale(sale);
            cacheService.cacheSale(uniqueKey, sale);
        }
    }

    private String generateUniqueKey(SaleDTO saleDTO) {
        String rawData = saleDTO.getCustomerName() +
                saleDTO.getLocalDate() +
                saleDTO.getTotalAmount();
        return DigestUtils.md5DigestAsHex(rawData.getBytes());
    }
}
