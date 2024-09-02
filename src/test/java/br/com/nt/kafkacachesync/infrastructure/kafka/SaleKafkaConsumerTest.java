package br.com.nt.kafkacachesync.infrastructure.kafka;

import br.com.nt.kafkacachesync.application.dto.SaleDTO;
import br.com.nt.kafkacachesync.application.mapper.SaleMapper;
import br.com.nt.kafkacachesync.domain.model.Sale;
import br.com.nt.kafkacachesync.domain.service.CacheService;
import br.com.nt.kafkacachesync.domain.service.SaleService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;

import static org.mockito.Mockito.*;

class SaleKafkaConsumerTest {

    private final SaleService saleService = Mockito.mock(SaleService.class);
    private final CacheService cacheService = Mockito.mock(CacheService.class);
    private final SaleMapper saleMapper = Mockito.mock(SaleMapper.class);
    private final SaleKafkaConsumer saleKafkaConsumer = new SaleKafkaConsumer(saleService, cacheService, saleMapper);

    @Test
    void shouldConsumeAndProcessSaleDTO() {

        SaleDTO saleDTO = new SaleDTO();
        saleDTO.setSaleId(1023L);
        saleDTO.setCustomerName("John Doe");
        saleDTO.setTotalAmount(150.0);
        saleDTO.setDiscountAmount(10.0);
        saleDTO.setLocalDate(LocalDate.now().toString());

        Sale expectedSale = SaleMapper.toEntity(saleDTO);
        when(cacheService.isSaleCached(Mockito.anyString())).thenReturn(false);

        saleKafkaConsumer.consume(saleDTO);

        verify(saleService).saveSale(any(Sale.class));
        verify(saleService).saveSale(argThat(sale ->
                sale.getSaleId().equals(expectedSale.getSaleId()) &&
                        sale.getCustomerName().equals(expectedSale.getCustomerName()) &&
                        sale.getTotalAmount() == expectedSale.getTotalAmount()
        ));
    }

    @Test
    void shouldNotProcessCachedSale() {

        SaleDTO saleDTO = new SaleDTO();
        saleDTO.setSaleId(123L);
        saleDTO.setCustomerName("John Doe");
        saleDTO.setTotalAmount(150.0);
        saleDTO.setDiscountAmount(10.0);
        saleDTO.setLocalDate(LocalDate.now().toString());
        when(cacheService.isSaleCached(Mockito.anyString())).thenReturn(true);

        saleKafkaConsumer.consume(saleDTO);

        verify(saleService, times(0)).saveSale(Mockito.any(Sale.class));
        verify(cacheService, times(0)).cacheSale(Mockito.anyString(), Mockito.any(Sale.class));
    }
}

