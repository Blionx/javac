package com.mercadolibre.pens_luis_bootcamp_final.unit.services;


import com.mercadolibre.pens_luis_bootcamp_final.dto.responses.BasicResponseDto;
import com.mercadolibre.pens_luis_bootcamp_final.dto.responses.GenerateOrderResponseDto;
import com.mercadolibre.pens_luis_bootcamp_final.dto.responses.OrderStatusDto;
import com.mercadolibre.pens_luis_bootcamp_final.exceptions.ApiException;
import com.mercadolibre.pens_luis_bootcamp_final.models.*;
import com.mercadolibre.pens_luis_bootcamp_final.repositories.*;
import com.mercadolibre.pens_luis_bootcamp_final.services.OrderServiceImpl;
import com.mercadolibre.pens_luis_bootcamp_final.unit.fixtures.OrderFixture;
import com.mercadolibre.pens_luis_bootcamp_final.unit.fixtures.PartsFixture;
import com.mercadolibre.pens_luis_bootcamp_final.util.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class OrderServiceImplTest {
    private OrderServiceImpl service;
    private OrderRepository orderRepositoryMock;
    private OrderMapper orderMapperMock;
    private OrderStatusMapper orderStatusMapperMock;
    private ConcessioanrieRepository repoConcessionaryMock;
    private DeliveryStatusRepository repoDeliveryStatusMock;
    private CentralHouseRepository repoCentralHouse;
    private ShippingTypeRepository repoShippingType;
    private PartRepository repoParts;
    private OrderUtil orderUtil;
    private OrderDetailRepository repoOrderDetail;
    private StockCentralHouseRepository repoCentralHouseStock;
    private AccountTypeRepository repoAccountType;




    @BeforeEach
    void setUp() {
        orderRepositoryMock = Mockito.mock(OrderRepository.class);
        orderMapperMock = Mockito.mock(OrderMapper.class);
        orderStatusMapperMock = Mockito.mock(OrderStatusMapper.class);
        repoConcessionaryMock = Mockito.mock(ConcessioanrieRepository.class);
        repoDeliveryStatusMock = Mockito.mock(DeliveryStatusRepository.class);
        repoCentralHouse = Mockito.mock(CentralHouseRepository.class);
        repoShippingType = Mockito.mock(ShippingTypeRepository.class);
        repoParts = Mockito.mock(PartRepository.class);
        orderUtil = Mockito.mock(OrderUtil.class);
        repoCentralHouseStock = Mockito.mock(StockCentralHouseRepository.class);
        repoOrderDetail = Mockito.mock(OrderDetailRepository.class);
        repoAccountType = Mockito.mock(AccountTypeRepository.class);
        service = new OrderServiceImpl(repoConcessionaryMock, repoDeliveryStatusMock,
                orderRepositoryMock, orderMapperMock, orderStatusMapperMock, repoCentralHouse, repoShippingType, repoParts
        , orderUtil, repoOrderDetail,repoCentralHouseStock, repoAccountType);
    }

    @Test
    @DisplayName("Main service method successful")
    void getOrders1() throws Exception {
        Mockito.when(orderRepositoryMock.findOrderByConcessionarieIdEqualsAndCentralHouseIdEqualsAndOrderNumberCMEquals
                (Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(OrderFixture.defaultOrderList());

        Mockito.when(orderStatusMapperMock.mapList(Mockito.any())).thenReturn(OrderFixture.defaultOrderStatusDto1());
        OrderStatusDto expected = OrderFixture.defaultOrderStatusDto1();
        OrderStatusDto actual = service.getOrderStatus("0001-0001-00000001");
        assertEquals(expected, actual);
    }

    /*

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

     */

    @Test
    @DisplayName("Invalid code exceptions test")
    void invalidCodeExceptionsTest() throws Exception{
        String originalCode = "0001-001-00000001";
        assertThrows(ApiException.class, () -> service.getOrderStatus(originalCode));
    }

    @Test
    @DisplayName("Invalid central house code exceptions test")
    void invalidCentralHouseCodeExceptionsTest() throws Exception{
        String originalCode = "1-0001-00000001";
        Exception e = assertThrows(ApiException.class,
                () -> service.getOrderStatus(originalCode));
        assertEquals("Central House code must have 4 digits", e.getMessage());
    }

    @Test
    @DisplayName("Invalid dealer code exceptions test")
    void invalidDealerCodeExceptionsTest() throws Exception{
        String originalCode = "0001-2-00000001";
        Exception e = assertThrows(ApiException.class,
                () -> service.getOrderStatus(originalCode));
        assertEquals("Dealer code must have 4 digits", e.getMessage());
    }
    @Test
    @DisplayName("Invalid order code exceptions test")
    void invalidOrderCodeExceptionsTest() throws Exception{
        String originalCode = "0001-0001-0001";
        Exception e = assertThrows(ApiException.class,
                () -> service.getOrderStatus(originalCode));
        assertEquals("Order code must have 8 digits", e.getMessage());
    }

    @Test
    @DisplayName("Main service method successful")
    void invalidOrderThrowsException() throws Exception {
        String validCode = "0001-0001-00000001";
        Long validDealerNumber = 1L;
        Integer invalidOrder = 3;
        Order order = OrderFixture.defaultOrder1();
        Concessionarie concessionarie = order.getConcessionarie();
        List<Concessionarie> concessionaries = new ArrayList<>();
        concessionaries.add(concessionarie);
        Mockito.when(repoConcessionaryMock.findById(Mockito.any())).thenReturn(concessionaries.stream().findAny());
        Mockito.when(orderRepositoryMock.findOrderByConcessionarieIdEquals(order.getConcessionarie().getId())).
                thenReturn(OrderFixture.defaultOrderList());
        Exception e = assertThrows(ApiException.class,
                () -> service.getOrders(validDealerNumber,"",invalidOrder));
        assertEquals("order must be between 1 or 2", e.getMessage());
    }

    @Test
    @DisplayName("unexisting order throws exception")
    void unexistingOrderThrowsExceptionTest() throws Exception {
        String validCode = "0001-0001-00000001";
        Long validDealerNumber = 1L;
        Integer validOrder = 2;
        Order order = OrderFixture.defaultOrder1();
        Concessionarie concessionarie = order.getConcessionarie();
        List<Concessionarie> concessionaries = new ArrayList<>();
        concessionaries.add(concessionarie);
        Mockito.when(repoConcessionaryMock.findById(Mockito.any())).thenReturn(concessionaries.stream().findAny());
        Mockito.when(orderRepositoryMock.findOrderByConcessionarieIdEquals(order.getConcessionarie().getId())).
                thenReturn(new ArrayList<Order>());
        Exception e = assertThrows(ApiException.class,
                () -> service.getOrders(validDealerNumber,"",validOrder));
        assertEquals("No orders found for this concessionaire", e.getMessage());
    }

    @Test
    @DisplayName("No concessionarie found exception")
    void noConcessionarieFoundExceptionTest() throws Exception{
        Integer validOrder = 2;
        Optional<Concessionarie> option = Optional.empty();
        Mockito.when(repoConcessionaryMock.findById(Mockito.any())).thenReturn(option);
        Exception e = assertThrows(ApiException.class,
                () -> service.getOrders(2L,"",validOrder));
        assertEquals("Concessionary not found", e.getMessage());

    }


    @Test
    @DisplayName("Invalid delivery status throws exception")
    void invalidDeliveryStatusThrowsExceptionTest() throws Exception {
        String validCode = "0001-0001-00000001";
        Long validDealerNumber = 1L;
        Integer validOrder = 2;
        String invalidDeliveryStatus = "Z";
        Order order = OrderFixture.defaultOrder1();
        Concessionarie concessionarie = order.getConcessionarie();
        List<Concessionarie> concessionaries = new ArrayList<>();
        concessionaries.add(concessionarie);
        Mockito.when(repoConcessionaryMock.findById(Mockito.any())).thenReturn(concessionaries.stream().findAny());
        ArrayList<DeliveryStatus> emptyDeliveryStatus = new ArrayList<>();
        Mockito.when(repoDeliveryStatusMock.findByCodeEquals(invalidDeliveryStatus)).thenReturn(emptyDeliveryStatus);
        Exception e = assertThrows(ApiException.class,
                () -> service.getOrders(validDealerNumber, invalidDeliveryStatus, validOrder));
        assertEquals("No such delivery status found", e.getMessage());
    }

    @Test
    @DisplayName("Update order status 1/N")
    void updateOrderStatus1() {
        Exception e = assertThrows(ApiException.class,
                () -> service.updateOrderStatus(2, "string"));
        assertEquals("Order not found", e.getMessage());
    }

    @Test
    @DisplayName("Update order status 2/N")
    void updateOrderStatus2() {
        Mockito.when(orderRepositoryMock.findByOrderNumberCMEquals(Mockito.any()))
                .thenReturn(Optional.of(new Order()));
        Exception e = assertThrows(ApiException.class,
                () -> service.updateOrderStatus(2, "string"));
        assertEquals("Delivery Status not found", e.getMessage());
    }

    @Test
    @DisplayName("Update order status 3/N")
    void updateOrderStatus3() {
        Mockito.when(orderRepositoryMock.findByOrderNumberCMEquals(Mockito.any()))
                .thenReturn(Optional.of(OrderFixture.defaultOrder1()));
        Mockito.when(repoDeliveryStatusMock.findByCodeEquals(Mockito.any()))
                .thenReturn(OrderFixture.defaultDeliveryStatusList());
        Mockito.when(repoCentralHouseStock.findByPartIdEqualsAndCentralHouseIdEquals(Mockito.any(), Mockito.any()))
                .thenReturn(OrderFixture.defaulStockCentralHouse());
        BasicResponseDto expected = new BasicResponseDto(HttpStatus.OK, "Order status updated");
        BasicResponseDto actual = service.updateOrderStatus(2, "string");
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Generate order")
    void generateOrder() {
        Mockito.when(repoCentralHouse.findById(Mockito.any()))
                .thenReturn(Optional.of(new CentralHouse()));
        Mockito.when(repoShippingType.findByNameEquals(Mockito.any()))
                .thenReturn(Optional.of(new ShippingType()));
        Mockito.when(repoConcessionaryMock.findById(Mockito.any()))
                .thenReturn(Optional.of(new Concessionarie()));
        Mockito.when(repoDeliveryStatusMock.findByCodeEquals(Mockito.any()))
                .thenReturn(OrderFixture.defaultDeliveryStatusList());
        Mockito.when(orderRepositoryMock.findAll())
                .thenReturn(OrderFixture.defaultOrderList());
        Mockito.when(orderUtil.generateOrder(Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any()))
                .thenReturn(OrderFixture.defaultOrder1());
        Mockito.when(repoParts.findPartByPartCode(Mockito.any())).thenReturn(Optional.of(PartsFixture.defaultPart1()));
        GenerateOrderResponseDto actual = service.generateOrder(OrderFixture.defaultOrderRequestDto());
        GenerateOrderResponseDto expected = new GenerateOrderResponseDto(OrderFixture.defaultOrder1().getOrderNumberCM().toString(), OrderFixture.defaultOrder1().getCentralHouse().getId());
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Validate status exception 1/3")
    void validateStatus1() {
        Exception e = assertThrows(ApiException.class,
                () -> service.validateOrderStatus(OrderFixture.defaultDeliveryStatusC(),
                        OrderFixture.defaultOrderStatusC()));
        assertEquals("Order is already in that state", e.getMessage());
    }

    @Test
    @DisplayName("Validate status exception 2/3")
    void validateStatus2() {
        Exception e = assertThrows(ApiException.class,
                () -> service.validateOrderStatus(OrderFixture.defaultDeliveryStatusF(),
                        OrderFixture.defaultOrderStatusC()));
        assertEquals("Order canceled, you can't update the status", e.getMessage());
    }

    @Test
    @DisplayName("Validate status exception 3/3")
    void validateStatus3() {
        Exception e = assertThrows(ApiException.class,
                () -> service.validateOrderStatus(OrderFixture.defaultDeliveryStatusC(),
                        OrderFixture.defaultOrderStatusF()));
        assertEquals("Order finished, you can't update the status", e.getMessage());
    }

    @Test
    @DisplayName("Validate request 1/3")
    void validateRequest11() {
        Exception e = assertThrows(ApiException.class,
                () -> service.validateRequest(OrderFixture.defaultOrderRequestDto()));
        assertEquals("Central house not found", e.getMessage());
    }

    @Test
    @DisplayName("Validate request 2/3")
    void validateRequest2() {
        Mockito.when(repoCentralHouse.findById(Mockito.any())).thenReturn(Optional.of(new CentralHouse()));
        Exception e = assertThrows(ApiException.class,
                () -> service.validateRequest(OrderFixture.defaultOrderRequestDto()));
        assertEquals("Concessionary not found", e.getMessage());
    }

    @Test
    @DisplayName("Validate request 3/3")
    void validateRequest3() {
        Mockito.when(repoCentralHouse.findById(Mockito.any())).thenReturn(Optional.of(new CentralHouse()));
        Mockito.when(repoConcessionaryMock.findById(Mockito.any())).thenReturn(Optional.of(new Concessionarie()));
        Mockito.when(repoShippingType.findById(Mockito.any())).thenReturn(null);
        Exception e = assertThrows(ApiException.class,
                () -> service.validateRequest(OrderFixture.defaultOrderRequestDto()));
        assertEquals("Shipping Type not found", e.getMessage());
    }

    @Test
    @DisplayName("Validate request parts 1/2")
    void validateRequestParts1() {
        Part part = PartsFixture.defaultPart1();
        Mockito.when(repoParts.findPartByPartCode(Mockito.any())).thenReturn(Optional.of(part));
        Exception e = assertThrows(ApiException.class,
                () -> service.validateRequestParts(OrderFixture.defaultOrderDetailDtoList2()));
        assertEquals("Insuficient stock for part: " + part.getDescription(), e.getMessage());
    }

    @Test
    @DisplayName("Validate request parts 2/2")
    void validateRequestParts2() {
        Part part = PartsFixture.defaultPart1();
        Exception e = assertThrows(ApiException.class,
                () -> service.validateRequestParts(OrderFixture.defaultOrderDetailDtoList()));
        assertEquals("Part with partCode:"+part.getPartCode()+" not found", e.getMessage());
    }






}
