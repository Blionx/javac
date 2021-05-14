package com.mercadolibre.pens_luis_bootcamp_final.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mercadolibre.pens_luis_bootcamp_final.util.DateMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewPartDto{
    @JsonProperty("partCode")
    private String partCode;
    private String description;
    @JsonProperty("providerId")
    private Long providerId;
    private Integer stock;
    @JsonProperty("netWeight")
    private Integer netWeight;
    @JsonProperty("longDimension")
    private Integer longDimension;
    @JsonProperty("widthDimension")
    private Integer widthDimension;
    @JsonProperty("talDimension")
    private Integer talDimension;
    @JsonProperty("lastModification")
    private LocalDate lastModification;
    @JsonProperty("discountTypeId")
    private Long discountTypeId;
    @JsonProperty("normalPrice")
    private Double normalPrice;
    @JsonProperty("urgentPrice")
    private Double urgentPrice;

    public void setLastModification(String lastModification) throws Exception {
        this.lastModification = DateMapper.mappearFecha(lastModification);
    }
}
