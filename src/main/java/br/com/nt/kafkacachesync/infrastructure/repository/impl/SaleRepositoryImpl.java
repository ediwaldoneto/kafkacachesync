package br.com.nt.kafkacachesync.infrastructure.repository.impl;

import br.com.nt.kafkacachesync.domain.model.Sale;
import br.com.nt.kafkacachesync.domain.repository.SaleRepository;
import br.com.nt.kafkacachesync.infrastructure.repository.JpaSaleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class SaleRepositoryImpl implements SaleRepository {

    private final JpaSaleRepository saleRepository;

    @Override
    public void save(Sale sale) {
        saleRepository.save(sale);
    }
}
