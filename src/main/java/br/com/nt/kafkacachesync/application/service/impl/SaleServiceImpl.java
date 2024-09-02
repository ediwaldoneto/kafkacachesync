package br.com.nt.kafkacachesync.application.service.impl;

import br.com.nt.kafkacachesync.domain.model.Sale;
import br.com.nt.kafkacachesync.domain.repository.SaleRepository;
import br.com.nt.kafkacachesync.domain.service.SaleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SaleServiceImpl implements SaleService {

    private final SaleRepository saleRepository;

    @Override
    public void saveSale(Sale sale) {
        saleRepository.save(sale);
    }

}
