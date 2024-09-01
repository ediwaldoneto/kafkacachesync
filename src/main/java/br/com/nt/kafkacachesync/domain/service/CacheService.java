package br.com.nt.kafkacachesync.domain.service;

import br.com.nt.kafkacachesync.domain.model.Sale;

public interface CacheService {

    boolean isSaleCached(String uniqueKey);

    public void cacheSale(String uniqueKey, Sale sale);

}
