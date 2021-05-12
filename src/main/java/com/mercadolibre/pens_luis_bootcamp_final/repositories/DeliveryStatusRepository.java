package com.mercadolibre.pens_luis_bootcamp_final.repositories;

import com.mercadolibre.pens_luis_bootcamp_final.models.Concessionarie;
import com.mercadolibre.pens_luis_bootcamp_final.models.DeliveryStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeliveryStatusRepository extends JpaRepository<DeliveryStatus, Long> {

    List<DeliveryStatus> findByCodeEquals(String code);
}
