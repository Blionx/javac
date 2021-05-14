package com.mercadolibre.pens_luis_bootcamp_final.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PriceVarianceItemDto {
    String date;
    double normalPrice;
    double urgentPrice;
    double normalPriceVariation;
    double urgentPriceVariation;

}
