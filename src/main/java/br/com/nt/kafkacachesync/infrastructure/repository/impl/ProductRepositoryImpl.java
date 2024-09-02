package br.com.nt.kafkacachesync.infrastructure.repository.impl;

import br.com.nt.kafkacachesync.domain.model.Product;
import br.com.nt.kafkacachesync.domain.repository.ProductRepository;
import br.com.nt.kafkacachesync.infrastructure.repository.JpaProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {

    private final JpaProductRepository jpaProductRepository;

    @Override
    public void save(Product product) {
        jpaProductRepository.save(product);
    }
}
