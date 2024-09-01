package br.com.nt.kafkacachesync.application.mapper;

import br.com.nt.kafkacachesync.application.dto.ProductDTO;
import br.com.nt.kafkacachesync.application.dto.SaleDTO;
import br.com.nt.kafkacachesync.domain.model.Product;
import br.com.nt.kafkacachesync.domain.model.Sale;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class SaleMapper {

    private SaleMapper() {
    }

    public static Sale toEntity(SaleDTO saleDTO) {
        if (saleDTO == null) {
            return null;
        }

        Sale sale = new Sale();
        sale.setSaleId(saleDTO.getSaleId());
        sale.setCustomerName(saleDTO.getCustomerName());
        sale.setTotalAmount(saleDTO.getTotalAmount());
        sale.setDiscountAmount(saleDTO.getDiscountAmount());
        sale.setLocalDate(LocalDate.parse(saleDTO.getLocalDate()));

        if (saleDTO.getProducts() != null) {
            List<Product> products = new ArrayList<>();
            for (ProductDTO productDTO : saleDTO.getProducts()) {
                Product product = ProductMapper.toEntity(productDTO);
                product.setSale(sale);
                products.add(product);
            }
            sale.setProducts(products);
        }

        return sale;
    }

}
