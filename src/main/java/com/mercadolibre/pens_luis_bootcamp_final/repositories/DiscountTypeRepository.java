package com.mercadolibre.pens_luis_bootcamp_final.repositories;

import com.mercadolibre.pens_luis_bootcamp_final.models.DiscountType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscountTypeRepository extends JpaRepository<DiscountType, Long> {
}
