package br.com.nt.kafkacachesync.infrastructure.repository;

import br.com.nt.kafkacachesync.domain.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaSaleRepository extends JpaRepository<Sale, Long> {
}
