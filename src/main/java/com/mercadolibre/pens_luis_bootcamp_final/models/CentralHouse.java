package com.mercadolibre.pens_luis_bootcamp_final.models;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "central_houses")
public class CentralHouse {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @NotNull
    private Long id;
    private String country;
    private Integer phone;
    private String name;
    private String address;
    @OneToMany(mappedBy = "centralHouse")
    private List<Order> orders;
    @OneToMany(mappedBy = "centralHouse")
    private List<StockCentralHouse> centralHouseStocks;

}
