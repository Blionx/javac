package com.mercadolibre.pens_luis_bootcamp_final.models;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@ToString(exclude={"stock", "partRecords", "provider"})
@Table(name = "parts")
@FilterDef(name="upperdate", parameters=@ParamDef( name="fromDate", type="LocalDate" ) )
public class Part {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String partCode;
    private String description;
    @ManyToOne
    @JoinColumn(name = "provider_id", nullable = false)
    private Provider provider;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "stock_id", referencedColumnName = "id")
    private Stock stock;
    private Integer netWeight;
    private Integer longDimension;
    private Integer widthDimenion;
    private Integer talDimension;
    private LocalDate lastModification;
    @OneToMany(mappedBy = "part",cascade = CascadeType.ALL)
    @Filter(name="upperdate", condition=":fromDate <= last_modification")
    private List<PartRecord> partRecords;
    @OneToMany(mappedBy = "part")
    private List<OrderDetail> orderDetails;
    @OneToMany(mappedBy = "part")
    private List<StockCentralHouse> stockCentralHouses;
}