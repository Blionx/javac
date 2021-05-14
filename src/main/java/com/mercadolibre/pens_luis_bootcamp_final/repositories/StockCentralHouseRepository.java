package com.mercadolibre.pens_luis_bootcamp_final.repositories;

import com.mercadolibre.pens_luis_bootcamp_final.models.StockCentralHouse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockCentralHouseRepository extends JpaRepository<StockCentralHouse, Long> {
    StockCentralHouse findByPartIdEqualsAndCentralHouseIdEquals(Long partId, Long centralHouseId);
}
