package com.mercadolibre.pens_luis_bootcamp_final.dto.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenerateOrderResponseDto {
    @JsonProperty("orderCMId")
    private String orderCMId;
    @JsonProperty("centralHouseId")
    private Long centralHouseId;


}
