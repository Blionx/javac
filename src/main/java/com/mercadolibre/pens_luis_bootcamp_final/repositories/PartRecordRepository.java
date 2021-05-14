package com.mercadolibre.pens_luis_bootcamp_final.repositories;

import com.mercadolibre.pens_luis_bootcamp_final.models.PartRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PartRecordRepository extends JpaRepository<PartRecord, Long> {
    public List<PartRecord> findByLastModificationAfter(LocalDate date);
}
