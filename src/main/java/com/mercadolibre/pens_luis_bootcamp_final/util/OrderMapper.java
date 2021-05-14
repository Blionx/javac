package com.mercadolibre.pens_luis_bootcamp_final.util;

import com.mercadolibre.pens_luis_bootcamp_final.dto.OrderDetailDto;
import com.mercadolibre.pens_luis_bootcamp_final.dto.OrderDto;
import com.mercadolibre.pens_luis_bootcamp_final.models.Order;
import com.mercadolibre.pens_luis_bootcamp_final.models.OrderDetail;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class OrderMapper {

    public List<OrderDto> mapList(List<Order> orders) {
        List<OrderDto> list = new ArrayList<>();
        for(Order o : orders) {
            list.add(map(o));
        }
        return list;
    }

    public List<OrderDetailDto>  mapOrderDetails(List<OrderDetail> orderDetails){
        List<OrderDetailDto> response = new ArrayList<>();
        for(OrderDetail od : orderDetails){
            OrderDetailDto orderDetailDto = new OrderDetailDto();
            orderDetailDto.setDescription(od.getDescription());
            orderDetailDto.setQuantity(od.getQuantity());
            orderDetailDto.setPartCode(od.getPart().getPartCode());
            orderDetailDto.setReason(od.getReason());
            orderDetailDto.setAccountType(od.getAccountType().getName());
            response.add(orderDetailDto);
        }
        return response;
    }

    public OrderDto map(Order order){
        OrderDto orderDto = new OrderDto();
        orderDto.setOrderNumber(order.getOrderNumberCM());
        orderDto.setOrderDate(order.getOrderDate().toString());
        orderDto.setDeliveryDate(order.getDeliveryDate().toString());
        orderDto.setDaysDelayed(order.getDaysDelayed());
        orderDto.setDeliveryStatus(order.getDeliveryStatus().getCode());
        orderDto.setOrderDetails(mapOrderDetails(order.getOrderDetails()));
        return orderDto;
    }
}
