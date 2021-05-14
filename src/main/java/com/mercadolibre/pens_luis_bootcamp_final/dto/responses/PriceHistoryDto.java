package com.mercadolibre.pens_luis_bootcamp_final.dto.responses;

import com.mercadolibre.pens_luis_bootcamp_final.dto.PriceVarianceItemDto;
import lombok.Data;

import java.util.List;

@Data
public class PriceHistoryDto {
    double startingNormalPrice;
    double endingNormalPrice;
    double startingUrgentPrice;
    double endingUrgentPrice;
    List<PriceVarianceItemDto> priceChangeVarianceList;
    double normalPriceVariance;
    double UrgentPriceVariance;
}
