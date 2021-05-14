package com.mercadolibre.pens_luis_bootcamp_final.unit.fixtures;

import com.mercadolibre.pens_luis_bootcamp_final.dto.OrderDetailDto;
import com.mercadolibre.pens_luis_bootcamp_final.dto.OrderDto;
import com.mercadolibre.pens_luis_bootcamp_final.dto.OrderRequestDto;
import com.mercadolibre.pens_luis_bootcamp_final.dto.responses.OrderStatusDto;
import com.mercadolibre.pens_luis_bootcamp_final.models.*;

import java.util.List;
import java.util.ArrayList;



import java.time.LocalDate;

public class OrderFixture {

    public static StockCentralHouse defaulStockCentralHouse() {
        StockCentralHouse sch = new StockCentralHouse();
        sch.setQuantity(3);
        return sch;
    }

    public static List<DeliveryStatus> defaultDeliveryStatusList() {
        List<DeliveryStatus> list = new ArrayList<>();
        list.add(defaultDeliveryStatusC());
        return list;
    }

    public static DeliveryStatus defaultDeliveryStatusF(){
        DeliveryStatus deliveryStatus = new DeliveryStatus();
        deliveryStatus.setDescription("Finalizado");
        deliveryStatus.setCode("F");
        deliveryStatus.setOrders(defaultOrderList());
        return deliveryStatus;
    }

    public static DeliveryStatus defaultDeliveryStatusC(){
        DeliveryStatus deliveryStatus = new DeliveryStatus();
        deliveryStatus.setDescription("Cancelado");
        deliveryStatus.setCode("C");
        return deliveryStatus;
    }

    public static Order defaultOrderStatusP() {
        Order order = new Order();
        order.setId(1L);
        order.setOrderNumberCM(3);
        order.setOrderDate(LocalDate.of(2020, 05,23));
        order.setDeliveryDate(LocalDate.of(2020, 11, 9));
        order.setDaysDelayed(10);
        DeliveryStatus ds = new DeliveryStatus();
        ds.setCode("P");
        ds.setDescription("Pendiente");
        order.setDeliveryStatus(ds);
        List<OrderDetail> orderDetails = new ArrayList();
        OrderDetail detail = defaultOrderDetail1();
        orderDetails.add(detail);
        order.setOrderDetails(orderDetails);
        CentralHouse centralHouse = new CentralHouse();
        centralHouse.setId(1L);
        centralHouse.setCountry("Argentina");
        centralHouse.setPhone(1231232);
        centralHouse.setName("vofi");
        centralHouse.setAddress("Ambrosio Olmos 878");
        order.setCentralHouse(centralHouse);
        Concessionarie conce = new Concessionarie();
        conce.setId(1L);
        conce.setAddress("25 de mayo 425");
        conce.setName("vofi2");
        conce.setPhone(12312123);
        conce.setCountry("Argentina");
        order.setConcessionarie(conce);
        ShippingType st = new ShippingType();
        st.setId(1L);
        st.setName("prueba");
        st.setDescription("desc prueba");
        order.setShippingType(st);
        return order;
    }
    public static Order defaultOrderStatusC() {
        Order order = new Order();
        order.setId(1L);
        order.setOrderNumberCM(3);
        order.setOrderDate(LocalDate.of(2020, 05,23));
        order.setDeliveryDate(LocalDate.of(2020, 11, 9));
        order.setDaysDelayed(10);
        DeliveryStatus ds = new DeliveryStatus();
        ds.setDescription("Cancelado");
        ds.setCode("C");
        order.setDeliveryStatus(ds);
        List<OrderDetail> orderDetails = new ArrayList();
        OrderDetail detail = defaultOrderDetail1();
        orderDetails.add(detail);
        order.setOrderDetails(orderDetails);
        CentralHouse centralHouse = new CentralHouse();
        centralHouse.setId(1L);
        centralHouse.setCountry("Argentina");
        centralHouse.setPhone(1231232);
        centralHouse.setName("vofi");
        centralHouse.setAddress("Ambrosio Olmos 878");
        order.setCentralHouse(centralHouse);
        Concessionarie conce = new Concessionarie();
        conce.setId(1L);
        conce.setAddress("25 de mayo 425");
        conce.setName("vofi2");
        conce.setPhone(12312123);
        conce.setCountry("Argentina");
        order.setConcessionarie(conce);
        ShippingType st = new ShippingType();
        st.setId(1L);
        st.setName("prueba");
        st.setDescription("desc prueba");
        order.setShippingType(st);
        return order;
    }
    public static Order defaultOrderStatusF() {
        Order order = new Order();
        order.setId(1L);
        order.setOrderNumberCM(3);
        order.setOrderDate(LocalDate.of(2020, 05,23));
        order.setDeliveryDate(LocalDate.of(2020, 11, 9));
        order.setDaysDelayed(10);
        DeliveryStatus ds = new DeliveryStatus();
        ds.setDescription("Finalizado");
        ds.setCode("F");
        order.setDeliveryStatus(ds);
        List<OrderDetail> orderDetails = new ArrayList();
        OrderDetail detail = defaultOrderDetail1();
        orderDetails.add(detail);
        order.setOrderDetails(orderDetails);
        CentralHouse centralHouse = new CentralHouse();
        centralHouse.setId(1L);
        centralHouse.setCountry("Argentina");
        centralHouse.setPhone(1231232);
        centralHouse.setName("vofi");
        centralHouse.setAddress("Ambrosio Olmos 878");
        order.setCentralHouse(centralHouse);
        Concessionarie conce = new Concessionarie();
        conce.setId(1L);
        conce.setAddress("25 de mayo 425");
        conce.setName("vofi2");
        conce.setPhone(12312123);
        conce.setCountry("Argentina");
        order.setConcessionarie(conce);
        ShippingType st = new ShippingType();
        st.setId(1L);
        st.setName("prueba");
        st.setDescription("desc prueba");
        order.setShippingType(st);
        return order;
    }

    public static OrderRequestDto defaultOrderRequestDto(){
        OrderRequestDto orderRequestDto = new OrderRequestDto();
        orderRequestDto.setCentralHouseId(1L);
        orderRequestDto.setConsessionarieId(1L);
        orderRequestDto.setShippingType("Directo");
        orderRequestDto.setParts(defaultOrderDetailDtoList());
        return orderRequestDto;
    }

    public static List<OrderDetailDto> defaultOrderDetailDtoList() {
        List<OrderDetailDto> list = new ArrayList<>();
        list.add(defaultOrderDetailDto1());
        list.add(defaultOrderDetailDto2());
        return list;
    }

    public static List<OrderDetailDto> defaultOrderDetailDtoList2() {
        List<OrderDetailDto> list = new ArrayList<>();
        list.add(defaultOrderDetailDto3());
        return list;
    }

    public static List<OrderDto> defaultOrderDtoList() {
        List<OrderDto> list = new ArrayList<>();
        list.add(defaultOrderDto1());
        list.add(defaultOrderDto2());
        return list;
    }

    public static List<Order> defaultOrderList() {
        List<Order> list = new ArrayList<>();
        list.add(defaultOrder1());
        list.add(defaultOrder2());
        return list;
    }

    public static List<OrderDetail> defaultOrderDetailList() {
        List<OrderDetail> list = new ArrayList<>();
        list.add(defaultOrderDetail1());
        list.add(defaultOrderDetail2());
        return list;
    }

    public static OrderStatusDto defaultOrderStatusDto1(){
        OrderStatusDto orderStatusDto = new OrderStatusDto();
        orderStatusDto.setOrderNumberCE("0001-00000003");
        orderStatusDto.setOrderDate("2020-05-23");
        orderStatusDto.setOrderStatus("P");
        List<OrderDetailDto> orderDetails = new ArrayList<>();
        orderDetails.add(defaultOrderDetailDto1());
        orderStatusDto.setOrderDetails(orderDetails);


        return orderStatusDto;
    }

    public static OrderDto defaultOrderDto1() {
        List<OrderDetailDto> listOrderDetail = new ArrayList<>();
        listOrderDetail.add(defaultOrderDetailDto1());
        return new OrderDto(3, "2020-05-23", "2020-11-09", 10, "P", listOrderDetail);
    }

    public static OrderStatusDto defaultOrderStatusDto2() {
        return new OrderStatusDto
                ("0001-00000001","2020-11-09","P",defaultOrderDetailDtoList());
    }

    public static OrderDto defaultOrderDto2() {
        List<OrderDetailDto> listOrderDetail = new ArrayList<>();
        listOrderDetail.add(defaultOrderDetailDto2());
        return new OrderDto(4, "2020-05-23", "2020-02-09", 11, "P", listOrderDetail);
    }

    public static OrderDetailDto defaultOrderDetailDto1() {
        return new OrderDetailDto("82917292", "Some description", 4, "G", "Some reason");

    }
    public static OrderDetailDto defaultOrderDetailDto2() {
        return new OrderDetailDto("10293028", "Other description", 9, "G", "Other reason");
    }
    public static OrderDetailDto defaultOrderDetailDto3() {
        return new OrderDetailDto("82917292", "Some description", 38, "G", "Some reason");

    }

    public static Order defaultOrder2(){
        Order order = new Order();
        order.setId(2L);
        order.setOrderNumberCM(4);
        order.setOrderDate(LocalDate.of(2020, 5,23));
        order.setDeliveryDate(LocalDate.of(2020, 2, 9));
        order.setDaysDelayed(11);
        DeliveryStatus ds = new DeliveryStatus();
        ds.setCode("P");
        order.setDeliveryStatus(ds);
        List<OrderDetail> orderDetails = new ArrayList();
        OrderDetail detail = defaultOrderDetail2();
        orderDetails.add(detail);
        order.setOrderDetails(orderDetails);
        CentralHouse centralHouse = new CentralHouse();
        centralHouse.setId(1L);
        centralHouse.setCountry("Argentina");
        centralHouse.setPhone(1231232);
        centralHouse.setName("vofi");
        centralHouse.setAddress("Ambrosio Olmos 878");
        order.setCentralHouse(centralHouse);
        Concessionarie conce = new Concessionarie();
        conce.setId(1L);
        conce.setAddress("25 de mayo 425");
        conce.setName("vofi2");
        conce.setPhone(12312123);
        conce.setCountry("Argentina");
        order.setConcessionarie(conce);
        ShippingType st = new ShippingType();
        st.setId(1L);
        st.setName("prueba");
        st.setDescription("desc prueba");
        order.setShippingType(st);
        return order;
    }

    public static DeliveryStatus defaultDeliveryStatus() {
        DeliveryStatus ds = new DeliveryStatus();
        ds.setCode("P");
        return ds;
    }

    public static List<OrderDetail> defaultOrderDetail() {
        List<OrderDetail> orderDetails = new ArrayList();
        OrderDetail detail = defaultOrderDetail1();
        orderDetails.add(detail);
        return orderDetails;
    }

    public static CentralHouse defaultCentralHouse() {
        CentralHouse centralHouse = new CentralHouse();
        centralHouse.setId(1L);
        centralHouse.setCountry("Argentina");
        centralHouse.setPhone(1231232);
        centralHouse.setName("vofi");
        centralHouse.setAddress("Ambrosio Olmos 878");
        return centralHouse;
    }

    public static Concessionarie defaultConcessionarie() {
        Concessionarie conce = new Concessionarie();
        conce.setId(1L);
        conce.setAddress("25 de mayo 425");
        conce.setName("vofi2");
        conce.setPhone(12312123);
        conce.setCountry("Argentina");
        return conce;
    }

    public static ShippingType defaultShippingType() {
        ShippingType st = new ShippingType();
        st.setId(1L);
        st.setName("prueba");
        st.setDescription("desc prueba");
        return st;
    }

    public static Order defaultOrder() {
        Order order = new Order();
        order.setOrderNumberCM(3);
        order.setOrderDate(LocalDate.now());
        DeliveryStatus ds = new DeliveryStatus();
        ds.setCode("P");
        order.setDeliveryStatus(ds);
        CentralHouse centralHouse = new CentralHouse();
        centralHouse.setId(1L);
        centralHouse.setCountry("Argentina");
        centralHouse.setPhone(1231232);
        centralHouse.setName("vofi");
        centralHouse.setAddress("Ambrosio Olmos 878");
        order.setCentralHouse(centralHouse);
        Concessionarie conce = new Concessionarie();
        conce.setId(1L);
        conce.setAddress("25 de mayo 425");
        conce.setName("vofi2");
        conce.setPhone(12312123);
        conce.setCountry("Argentina");
        order.setConcessionarie(conce);
        ShippingType st = new ShippingType();
        st.setId(1L);
        st.setName("prueba");
        st.setDescription("desc prueba");
        order.setShippingType(st);
        order.setDaysDelayed(0);
        order.setDeliveryDate(LocalDate.now().plusDays(7));
        return order;
    }

    public static Order defaultOrder1() {
        Order order = new Order();
        order.setId(1L);
        order.setOrderNumberCM(3);
        order.setOrderDate(LocalDate.of(2020, 05,23));
        order.setDeliveryDate(LocalDate.of(2020, 11, 9));
        order.setDaysDelayed(10);
        DeliveryStatus ds = new DeliveryStatus();
        ds.setCode("P");
        order.setDeliveryStatus(ds);
        List<OrderDetail> orderDetails = new ArrayList();
        OrderDetail detail = defaultOrderDetail1();
        orderDetails.add(detail);
        order.setOrderDetails(orderDetails);
        CentralHouse centralHouse = new CentralHouse();
        centralHouse.setId(1L);
        centralHouse.setCountry("Argentina");
        centralHouse.setPhone(1231232);
        centralHouse.setName("vofi");
        centralHouse.setAddress("Ambrosio Olmos 878");
        order.setCentralHouse(centralHouse);
        Concessionarie conce = new Concessionarie();
        conce.setId(1L);
        conce.setAddress("25 de mayo 425");
        conce.setName("vofi2");
        conce.setPhone(12312123);
        conce.setCountry("Argentina");
        order.setConcessionarie(conce);
        ShippingType st = new ShippingType();
        st.setId(1L);
        st.setName("prueba");
        st.setDescription("desc prueba");
        order.setShippingType(st);
        return order;
    }

    public static OrderDetail defaultOrderDetail1() {
        OrderDetail detail = new OrderDetail();
        detail.setId(1L);
        detail.setReason("Some reason");
        detail.setDescription("Some description");
        detail.setQuantity(4);
        detail.setOrder(null);
        Part part = new Part();
        Stock stock = new Stock();
        stock.setQuantity(1);
        part.setStock(stock);
        part.setPartCode("82917292");
        detail.setPart(part);
        AccountType account = new AccountType();
        account.setName("G");
        detail.setAccountType(account);
        return detail;
    }

    public static OrderDetail defaultOrderDetail2() {
        OrderDetail detail = new OrderDetail();
        detail.setId(2L);
        detail.setReason("Other reason");
        detail.setDescription("Other description");
        detail.setQuantity(9);
        detail.setOrder(null);
        Part part = new Part();
        part.setPartCode("10293028");
        detail.setPart(part);
        AccountType account = new AccountType();
        account.setName("G");
        detail.setAccountType(account);
        return detail;
    }
}
