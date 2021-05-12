package com.mercadolibre.pens_luis_bootcamp_final.models;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "order_details")
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @NotNull
    private Long id;
    private String reason;
    private String description;
    private Integer quantity;
    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;
    @ManyToOne
    @JoinColumn(name = "part_id", nullable = false)
    private Part part;
    @ManyToOne
    @JoinColumn(name = "account_type_id", nullable = false)
    private AccountType accountType;
}
