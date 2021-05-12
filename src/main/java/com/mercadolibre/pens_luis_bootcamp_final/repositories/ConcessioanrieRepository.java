package com.mercadolibre.pens_luis_bootcamp_final.repositories;

import com.mercadolibre.pens_luis_bootcamp_final.models.CentralHouse;
import com.mercadolibre.pens_luis_bootcamp_final.models.Concessionarie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConcessioanrieRepository extends JpaRepository<Concessionarie, Long> {
}
