package br.com.demo.salesapp.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SaleDTO {

    private String saleId;
    private String customerName;
    private List<String> productIds;
    private double totalAmount;
}
