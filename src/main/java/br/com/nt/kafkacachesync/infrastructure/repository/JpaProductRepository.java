package br.com.nt.kafkacachesync.infrastructure.repository;

import br.com.nt.kafkacachesync.domain.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaProductRepository extends JpaRepository<Product, Long> {
}
