package com.mercadolibre.pens_luis_bootcamp_final.services;

import com.mercadolibre.pens_luis_bootcamp_final.dtos.OrderRequestDto;
import com.mercadolibre.pens_luis_bootcamp_final.dtos.responses.BasicResponseDto;
import com.mercadolibre.pens_luis_bootcamp_final.dtos.responses.GenerateOrderResponseDto;
import com.mercadolibre.pens_luis_bootcamp_final.dtos.responses.OrderResponseDto;
import com.mercadolibre.pens_luis_bootcamp_final.dtos.responses.OrderStatusDto;

public interface OrdersService {

    OrderResponseDto getOrders(Long dealerNumber, String deliveryStatusCode, Integer order) throws Exception;
    OrderStatusDto getOrderStatus(String code);
    GenerateOrderResponseDto generateOrder(OrderRequestDto request);
    BasicResponseDto updateOrderStatus(Integer orderNumberCM, String orderStatus);

}
