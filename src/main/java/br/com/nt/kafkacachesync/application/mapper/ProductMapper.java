package br.com.nt.kafkacachesync.application.mapper;

import br.com.nt.kafkacachesync.application.dto.ProductDTO;
import br.com.nt.kafkacachesync.domain.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    private ProductMapper() {
    }

    public static Product toEntity(ProductDTO productDTO) {
        if (productDTO == null) {
            return null;
        }

        Product product = new Product();
        product.setId(productDTO.getId());
        product.setProductId(productDTO.getProductId());

        return product;
    }

}
