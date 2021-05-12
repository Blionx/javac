package com.mercadolibre.pens_luis_bootcamp_final.models;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "account_types")
public class AccountType {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @NotNull
    private Long id;
    private String name;
    private String description;
    @OneToMany(mappedBy = "accountType")
    private List<OrderDetail> orderDetails;
}
