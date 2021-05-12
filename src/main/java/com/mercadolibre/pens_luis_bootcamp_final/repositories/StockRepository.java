package com.mercadolibre.pens_luis_bootcamp_final.repositories;

import com.mercadolibre.pens_luis_bootcamp_final.models.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long>  {
}
