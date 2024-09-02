package br.com.nt.kafkacachesync.application.mapper;

import br.com.nt.kafkacachesync.application.dto.ProductDTO;
import br.com.nt.kafkacachesync.domain.model.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(MockitoExtension.class)
class ProductMapperTest {


    @Test
    void shouldConvertProductDTOToProduct() {

        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(1L);
        productDTO.setProductId("P123");

        Product product = ProductMapper.toEntity(productDTO);

        assertThat(product).isNotNull();
        assertThat(product.getId()).isEqualTo(1L);
        assertThat(product.getProductId()).isEqualTo("P123");
    }

    @Test
    void shouldReturnNullWhenProductDTOIsNull() {
        ProductDTO productDTO = null;

        Product product = ProductMapper.toEntity(productDTO);

        assertThat(product).isNull();
    }
}