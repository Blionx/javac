package com.mercadolibre.pens_luis_bootcamp_final.models;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "providers")
public class Provider {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    private String address;
    private String phone;
    private String country;
    @OneToMany(mappedBy = "provider")
    private List<Part> parts;
}
