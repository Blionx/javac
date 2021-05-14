package com.mercadolibre.pens_luis_bootcamp_final.util;

import com.mercadolibre.pens_luis_bootcamp_final.dto.responses.OrderStatusDto;
import com.mercadolibre.pens_luis_bootcamp_final.models.Order;
import org.springframework.stereotype.Component;


@Component
public class OrderStatusMapper {

    public OrderStatusDto mapList(Order order) {
        return map(order);
    }

    public OrderStatusDto map(Order order){
        OrderStatusDto orderDto = new OrderStatusDto();
        orderDto.setOrderNumberCE(
                intToCodeString(4,
                        String.valueOf(order.getConcessionarie().getId())) + "-"
                        + intToCodeString(8, String.valueOf(order.getOrderNumberCM())));
        orderDto.setOrderDate(order.getOrderDate().toString());
        orderDto.setOrderStatus(order.getDeliveryStatus().getCode());
        OrderMapper orderMapper = new OrderMapper();
        orderDto.setOrderDetails(orderMapper.mapOrderDetails(order.getOrderDetails()));
        return orderDto;
    }

    public String intToCodeString(int len, String aux) {
        String res = "";
        for (int j = 0; j < len - aux.length(); j++)
            res = res + "0";
        return res + aux;
    }
}
