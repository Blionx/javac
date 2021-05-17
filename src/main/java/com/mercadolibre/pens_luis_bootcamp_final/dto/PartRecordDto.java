package com.mercadolibre.pens_luis_bootcamp_final.dto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PartRecordDto {

    @JsonProperty("discountType")
    private Long discountType;
    @JsonProperty("normalPrice")
    private Double normalPrice;
    @JsonProperty("urgentPrice")
    private Double urgentPrice;
    @JsonProperty("partCode")
    private String partCode;
}
