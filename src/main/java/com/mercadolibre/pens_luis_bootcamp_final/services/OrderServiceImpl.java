package com.mercadolibre.pens_luis_bootcamp_final.services;

import com.mercadolibre.pens_luis_bootcamp_final.dtos.OrderDetailDto;
import com.mercadolibre.pens_luis_bootcamp_final.dtos.OrderDto;
import com.mercadolibre.pens_luis_bootcamp_final.dtos.OrderRequestDto;
import com.mercadolibre.pens_luis_bootcamp_final.dtos.responses.BasicResponseDto;
import com.mercadolibre.pens_luis_bootcamp_final.dtos.responses.GenerateOrderResponseDto;
import com.mercadolibre.pens_luis_bootcamp_final.dtos.responses.OrderResponseDto;
import com.mercadolibre.pens_luis_bootcamp_final.dtos.responses.OrderStatusDto;
import com.mercadolibre.pens_luis_bootcamp_final.exceptions.ApiException;
import com.mercadolibre.pens_luis_bootcamp_final.models.*;
import com.mercadolibre.pens_luis_bootcamp_final.repositories.*;
import com.mercadolibre.pens_luis_bootcamp_final.util.OrderMapper;
import com.mercadolibre.pens_luis_bootcamp_final.util.OrderStatusMapper;
import com.mercadolibre.pens_luis_bootcamp_final.util.OrderUtil;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;

@Service
public class OrderServiceImpl implements OrdersService{

    private OrderRepository repoOrders;
    private ConcessioanrieRepository repoConcessionary;
    private DeliveryStatusRepository repoDeliveryStatus;
    private OrderMapper orderMapper;
    private OrderUtil orderUtil;
    private OrderStatusMapper orderStatusMapper;
    private CentralHouseRepository repoCentralHouse;
    private ShippingTypeRepository repoShippingType;
    private PartRepository repoParts;
    private OrderDetailRepository repoOrderDetail;
    private StockCentralHouseRepository repoStockCentralHouse;
    private AccountTypeRepository repoAccountType;


    public OrderServiceImpl(ConcessioanrieRepository repoConcessionary, DeliveryStatusRepository repoDeliveryStatus,
                            OrderRepository repoOrders, OrderMapper mapper, OrderStatusMapper orderStatusMapper, CentralHouseRepository repoCH,
                            ShippingTypeRepository repoST, PartRepository repoP, OrderUtil oUtil, OrderDetailRepository repoOD, StockCentralHouseRepository repoCHS,
                            AccountTypeRepository repoAT){
        this.repoConcessionary = repoConcessionary;
        this.repoDeliveryStatus = repoDeliveryStatus;
        this.repoOrders = repoOrders;
        this.orderMapper = mapper;
        this.orderStatusMapper = orderStatusMapper;
        this.repoCentralHouse = repoCH;
        this.repoShippingType = repoST;
        this.repoParts = repoP;
        this.orderUtil = oUtil;
        this.repoOrderDetail = repoOD;
        this.repoStockCentralHouse = repoCHS;
        this.repoAccountType = repoAT;
    }


    public Integer validateOrder(Integer order){
        if (order < 1 || order > 2){
            throw new ApiException(HttpStatus.BAD_REQUEST.name(), "order must be between 1 or 2", HttpStatus.BAD_REQUEST.value());
        }
        return order;
    }

    private void orderOrders(Integer order, List<Order> orders) throws Exception {
        switch (order){
            case 1:
                orders.sort(Comparator.comparing(Order::getOrderNumberCM));
                break;

            case 2:
                orders.sort(Comparator.comparing(Order::getOrderNumberCM).reversed());
                break;
        }
    }

    @Transactional(readOnly=true)
    @Override
    public OrderResponseDto getOrders(Long dealerNumber, String deliveryStatusCode, Integer order) throws Exception {
        List<Order> orders = null;
        boolean filterStatusCode = !deliveryStatusCode.isEmpty();

        Concessionarie consessionary = repoConcessionary.findById(dealerNumber).orElse(null);
        if(consessionary == null){
            throw new ApiException
                    (HttpStatus.BAD_REQUEST.name(), "Concessionary not found", HttpStatus.BAD_REQUEST.value());
        }

        if (filterStatusCode){
            DeliveryStatus deliveryStatus = repoDeliveryStatus.findByCodeEquals(deliveryStatusCode)
                    .stream().findFirst().orElse(null);
            if (deliveryStatus == null){
                throw new ApiException
                        (HttpStatus.BAD_REQUEST.name(),
                                "No such delivery status found", HttpStatus.BAD_REQUEST.value());
            }

            orders = repoOrders.
                    findOrderByConcessionarieIdEqualsAndDeliveryStatusIdEquals(dealerNumber,deliveryStatus.getId());
        }
        else{
            orders = repoOrders.findOrderByConcessionarieIdEquals(dealerNumber);
        }
        if (orders.size() == 0){
            throw new ApiException
                    (HttpStatus.BAD_REQUEST.name(), "No orders found for this concessionaire", HttpStatus.BAD_REQUEST.value());
        }
        if(order > 0){
            validateOrder(order);
            orderOrders(order, orders);
        }
        List<OrderDto> orderDtos = orderMapper.mapList(orders);
        OrderResponseDto orderResponseDto = new OrderResponseDto(dealerNumber, orderDtos);
        return orderResponseDto;
    }

//    Validar largo strings
    @Transactional(readOnly=true)
    @Override
    public OrderStatusDto getOrderStatus(String code) {
        String[] res = code.split("-");
        validateCodes(res);
        Long dealerNumber = Long.parseLong(res[1]);
        Long numberCE = Long.parseLong(res[0]);
        Integer orderCode = Integer.parseInt(res[2]);
        List<Order> order = repoOrders.findOrderByConcessionarieIdEqualsAndCentralHouseIdEqualsAndOrderNumberCMEquals(dealerNumber, numberCE, orderCode);
        OrderStatusDto orderDtos = orderStatusMapper.mapList(order.get(0));
        return orderDtos;
    }

    @Override
    @Transactional
    public GenerateOrderResponseDto generateOrder(OrderRequestDto request) {
        validateRequest(request);
        validateRequestParts(request.getParts());
        CentralHouse centralHouse = repoCentralHouse.findById(request.getCentralHouseId()).orElse(null);
        ShippingType shippingType = repoShippingType.findByNameEquals(request.getShippingType()).orElse(null);
        Concessionarie concessionarie = repoConcessionary.findById(request.getConsessionarieId()).orElse(null);
        DeliveryStatus deliveryStatus = repoDeliveryStatus.findByCodeEquals("P").stream().findFirst().orElse(null);
        Order lastOrder = repoOrders.findAll().stream().
                sorted((a, b) -> b.getOrderNumberCM().compareTo(a.getOrderNumberCM())).findFirst().orElse(null);
        Integer newOrderNumberCM = lastOrder.getOrderNumberCM() + 1;

        Order newOrder = orderUtil.generateOrder(concessionarie, shippingType, centralHouse, deliveryStatus, newOrderNumberCM);

        List<OrderDetail> orderDetails = orderUtil.reverseMapOrderDetailDto(request.getParts(), newOrder);
        newOrder.setOrderDetails(orderDetails);
        AccountType accountType = repoAccountType.findByNameEquals("R").orElse(null);

        for(OrderDetail od: orderDetails)
        {
            updateStock(od.getPart(), od.getQuantity(),centralHouse);
            od.setAccountType(accountType);
        }

        repoOrders.save(newOrder);

        GenerateOrderResponseDto resp = new GenerateOrderResponseDto(newOrder.getOrderNumberCM().toString(), newOrder.getCentralHouse().getId());

        return resp;
    }

    @Override
    @Transactional
    public BasicResponseDto updateOrderStatus(Integer orderNumberCM, String orderStatus) {
        Order order = repoOrders.findByOrderNumberCMEquals(orderNumberCM).orElse(null);
        if (order == null){
            throw new ApiException
                    (HttpStatus.BAD_REQUEST.name(), "Order not found", HttpStatus.BAD_REQUEST.value());
        }

        DeliveryStatus deliveryStatus = repoDeliveryStatus.findByCodeEquals(orderStatus).stream().findFirst().orElse(null);
        if (deliveryStatus == null){
            throw new ApiException
                    (HttpStatus.BAD_REQUEST.name(), "Delivery Status not found", HttpStatus.BAD_REQUEST.value());
        }
        validateOrderStatus(deliveryStatus, order);

        if (deliveryStatus.getCode().equals("C")){
            List<OrderDetail> details = order.getOrderDetails();
            for(OrderDetail orderDetail: details){
                Integer stockCM = orderDetail.getPart().getStock().getQuantity();
                Integer quantityOrder = orderDetail.getQuantity();
                orderDetail.getPart().getStock().setQuantity(stockCM + quantityOrder);
                StockCentralHouse stockCentralHouse = repoStockCentralHouse.findByPartIdEqualsAndCentralHouseIdEquals(orderDetail.getPart().getId(),
                        order.getCentralHouse().getId());
                stockCentralHouse.setQuantity(stockCentralHouse.getQuantity() - quantityOrder);
                repoParts.save(orderDetail.getPart());
            }
        }
        order.setDeliveryStatus(deliveryStatus);
        repoOrders.save(order);

        return new BasicResponseDto(HttpStatus.OK, "Order status updated");
    }

    public void validateOrderStatus(DeliveryStatus deliveryStatus, Order order){
        if (order.getDeliveryStatus().equals(deliveryStatus)){
            throw new ApiException
                    (HttpStatus.BAD_REQUEST.name(), "Order is already in that state", HttpStatus.BAD_REQUEST.value());
        }
        if (order.getDeliveryStatus().getCode().equals("C")){
            throw new ApiException
                    (HttpStatus.BAD_REQUEST.name(), "Order canceled, you can't update the status", HttpStatus.BAD_REQUEST.value());
        }
        if (order.getDeliveryStatus().getCode().equals("F")){
            throw new ApiException
                    (HttpStatus.BAD_REQUEST.name(), "Order finished, you can't update the status", HttpStatus.BAD_REQUEST.value());
        }
    }

    public void validateRequest(OrderRequestDto req){
        CentralHouse centralHouse = repoCentralHouse.findById(req.getCentralHouseId()).orElse(null);
        if (centralHouse == null){
            throw new ApiException
                    (HttpStatus.BAD_REQUEST.name(), "Central house not found", HttpStatus.BAD_REQUEST.value());
        }
        Concessionarie consessionary = repoConcessionary.findById(req.getConsessionarieId()).orElse(null);
        if(consessionary == null){
            throw new ApiException
                    (HttpStatus.BAD_REQUEST.name(), "Concessionary not found", HttpStatus.BAD_REQUEST.value());
        }
        ShippingType shippingType = repoShippingType.findByNameEquals(req.getShippingType()).orElse(null);
        if(shippingType == null){
            throw new ApiException
                    (HttpStatus.BAD_REQUEST.name(), "Shipping Type not found", HttpStatus.BAD_REQUEST.value());
        }
    }

    public void validateRequestParts(List<OrderDetailDto> parts){
        for (OrderDetailDto oDetail: parts){
            Part part = repoParts.findPartByPartCode(oDetail.getPartCode()).orElse(null);
            if(part == null){
                throw new ApiException
                        (HttpStatus.BAD_REQUEST.name(), "Part with partCode:" + oDetail.getPartCode() + " not found", HttpStatus.BAD_REQUEST.value());
            }
            if (part.getStock().getQuantity() - oDetail.getQuantity() < 0){
                throw new ApiException
                        (HttpStatus.BAD_REQUEST.name(), "Insuficient stock for part: " + part.getDescription() , HttpStatus.BAD_REQUEST.value());
            }
        }
    }



    public void updateStock(Part part, Integer newStock, CentralHouse centralHouse){
        Integer currentStock = part.getStock().getQuantity();
        part.getStock().setQuantity(currentStock - newStock);
        StockCentralHouse stockCH = repoStockCentralHouse.findByPartIdEqualsAndCentralHouseIdEquals(part.getId(), centralHouse.getId());
        stockCH.setQuantity(stockCH.getQuantity() + newStock);
        repoStockCentralHouse.save(stockCH);
        repoParts.save(part);
    }




    private void validateCodes(String[] codes) {
        if (codes[0].length() != 4)
                throw new ApiException
                        (HttpStatus.UNPROCESSABLE_ENTITY.name(), "Central House code must have 4 digits", HttpStatus.UNPROCESSABLE_ENTITY.value());
        if (codes[1].length() != 4)
            throw new ApiException
                    (HttpStatus.UNPROCESSABLE_ENTITY.name(), "Dealer code must have 4 digits", HttpStatus.UNPROCESSABLE_ENTITY.value());
        if (codes[2].length() != 8)
            throw new ApiException
                    (HttpStatus.UNPROCESSABLE_ENTITY.name(), "Order code must have 8 digits", HttpStatus.UNPROCESSABLE_ENTITY.value());
    }



}
