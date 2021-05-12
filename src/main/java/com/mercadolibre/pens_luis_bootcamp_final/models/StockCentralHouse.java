package com.mercadolibre.pens_luis_bootcamp_final.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "stock_central_house")
public class StockCentralHouse {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "part_id", nullable = false)
    private Part part;
    @ManyToOne
    @JoinColumn(name = "central_house_id", nullable = false)
    private CentralHouse centralHouse;
    private Integer quantity;
}
