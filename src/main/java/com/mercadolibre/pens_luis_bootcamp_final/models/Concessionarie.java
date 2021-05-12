package com.mercadolibre.pens_luis_bootcamp_final.models;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "concessionaries")
public class Concessionarie {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @NotNull
    private Long id;
    private String address;
    private String name;
    private Integer phone;
    private String country;
    @OneToMany(mappedBy = "concessionarie")
    private List<Order> orders;
}
