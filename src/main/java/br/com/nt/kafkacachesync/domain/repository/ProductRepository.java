package br.com.nt.kafkacachesync.domain.repository;

import br.com.nt.kafkacachesync.domain.model.Product;

public interface ProductRepository {

    void save(final Product product);
}
