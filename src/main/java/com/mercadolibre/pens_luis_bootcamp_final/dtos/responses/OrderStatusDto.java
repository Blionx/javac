package com.mercadolibre.pens_luis_bootcamp_final.dtos.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mercadolibre.pens_luis_bootcamp_final.dtos.OrderDetailDto;
import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderStatusDto {
    @JsonProperty("orderNumberCE")
    private String orderNumberCE;
    @JsonProperty("orderDate")
    private String orderDate;
    @JsonProperty("orderStatus")
    private String orderStatus;
    @JsonProperty("orderDetails")
    private List<OrderDetailDto> orderDetails;

}
