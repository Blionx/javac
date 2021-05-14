package com.mercadolibre.pens_luis_bootcamp_final.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PartDto {
    @JsonProperty("partCode")
    private String partCode;
    private String description;
    private String marker;
    private String quantity;
    @JsonProperty("discountType")
    private String discountType;
    @JsonProperty("normalPrice")
    private String normalPrice;
    @JsonProperty("urgentPrice")
    private String urgentPrice;
    @JsonProperty("netWeight")
    private String netWeight;
    @JsonProperty("longDimension")
    private String longDimension;
    @JsonProperty("widthDimension")
    private String widthDimension;
    @JsonProperty("tallDimension")
    private String tallDimension;
    @JsonProperty("lastModification")
    private String lastModification;
}
