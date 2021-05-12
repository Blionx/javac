package com.mercadolibre.pens_luis_bootcamp_final.repositories;

import com.mercadolibre.pens_luis_bootcamp_final.models.Part;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface PartRepository extends JpaRepository<Part, Long> {
    List<Part> findByLastModificationAfter(LocalDate date);

    Optional<Part> findPartByPartCode(String code);
}
