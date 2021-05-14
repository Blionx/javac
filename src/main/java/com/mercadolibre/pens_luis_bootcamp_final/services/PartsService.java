package com.mercadolibre.pens_luis_bootcamp_final.services;

import com.mercadolibre.pens_luis_bootcamp_final.dto.NewPartDto;
import com.mercadolibre.pens_luis_bootcamp_final.dto.responses.PartResponseDto;
import com.mercadolibre.pens_luis_bootcamp_final.dto.responses.PriceHistoryDto;

public interface PartsService {
    PartResponseDto getParts(String queryType, String date, String order) throws Exception;
    Integer updateStock(String partCode, Integer quantity);
    NewPartDto createPart(NewPartDto newPart);
    PriceHistoryDto getPartPriceHistory(String partCode, String fromDate) throws Exception;
}
