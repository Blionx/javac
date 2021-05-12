package com.mercadolibre.pens_luis_bootcamp_final.repositories;

import com.mercadolibre.pens_luis_bootcamp_final.models.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
}
