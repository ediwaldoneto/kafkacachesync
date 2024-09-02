package br.com.nt.kafkacachesync.domain.repository;

import br.com.nt.kafkacachesync.domain.model.Sale;

public interface SaleRepository {

    void save(final Sale sale);
}
