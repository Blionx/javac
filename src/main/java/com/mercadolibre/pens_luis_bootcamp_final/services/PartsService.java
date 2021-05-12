package com.mercadolibre.pens_luis_bootcamp_final.services;

import com.mercadolibre.pens_luis_bootcamp_final.dtos.NewPartDto;
import com.mercadolibre.pens_luis_bootcamp_final.dtos.responses.PartResponseDto;

public interface PartsService {
    PartResponseDto getParts(String queryType, String date, String order) throws Exception;
    Integer updateStock(String partCode, Integer quantity);
    NewPartDto createPart(NewPartDto newPart);
}
