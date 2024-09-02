package br.com.nt.kafkacachesync.application.mapper;

import br.com.nt.kafkacachesync.application.dto.ProductDTO;
import br.com.nt.kafkacachesync.application.dto.SaleDTO;
import br.com.nt.kafkacachesync.domain.model.Sale;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Collections;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(MockitoExtension.class)
class SaleMapperTest {

    @Test
    void shouldConvertSaleDTOToSale() {

        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(1L);
        productDTO.setProductId("P123");

        SaleDTO saleDTO = new SaleDTO();
        saleDTO.setSaleId(123L);
        saleDTO.setCustomerName("John Doe");
        saleDTO.setTotalAmount(150.0);
        saleDTO.setDiscountAmount(10.0);
        saleDTO.setLocalDate(LocalDate.now().toString());
        saleDTO.setProducts(Collections.singletonList(productDTO));


        Sale sale = SaleMapper.toEntity(saleDTO);

        assertThat(sale).isNotNull();
        assertThat(sale.getSaleId()).isEqualTo(123L);
        assertThat(sale.getCustomerName()).isEqualTo("John Doe");
        assertThat(sale.getTotalAmount()).isEqualTo(150.0);
        assertThat(sale.getDiscountAmount()).isEqualTo(10.0);
        assertThat(sale.getLocalDate()).isEqualTo(LocalDate.now());
        assertThat(sale.getProducts().get(0)).isNotNull();
        assertThat(sale.getProducts().get(0).getProductId()).isEqualTo("P123");
        assertThat(sale.getProducts().get(0).getSale()).isEqualTo(sale);
    }

    @Test
    void shouldReturnNullWhenSaleDTOIsNull() {

        SaleDTO saleDTO = null;

        Sale sale = SaleMapper.toEntity(saleDTO);

        assertThat(sale).isNull();
    }

    @Test
    void shouldHandleEmptyProductList() {

        SaleDTO saleDTO = new SaleDTO();
        saleDTO.setSaleId(123L);
        saleDTO.setCustomerName("John Doe");
        saleDTO.setTotalAmount(150.0);
        saleDTO.setDiscountAmount(10.0);
        saleDTO.setLocalDate(LocalDate.now().toString());
        saleDTO.setProducts(Collections.emptyList());

        Sale sale = SaleMapper.toEntity(saleDTO);

        assertThat(sale).isNotNull();
        assert (sale.getProducts().isEmpty());
    }
}