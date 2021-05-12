package com.mercadolibre.pens_luis_bootcamp_final.models;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "discount_types")
public class DiscountType {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @NotNull
    private Long id;
    private String description;
    @OneToMany(mappedBy = "discountType")
    private List<PartRecord> partRecords;
}