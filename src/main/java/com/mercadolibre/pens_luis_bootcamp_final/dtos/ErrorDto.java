package com.mercadolibre.pens_luis_bootcamp_final.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDto {
    private String code;
    private String description;
    @JsonProperty("statusCode")
    private Integer statusCode;
}
