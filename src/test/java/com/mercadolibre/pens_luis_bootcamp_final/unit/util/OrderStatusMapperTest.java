package com.mercadolibre.pens_luis_bootcamp_final.unit.util;

import com.mercadolibre.pens_luis_bootcamp_final.dto.responses.OrderStatusDto;
import com.mercadolibre.pens_luis_bootcamp_final.unit.fixtures.OrderFixture;
import com.mercadolibre.pens_luis_bootcamp_final.util.MockitoExtension;
import com.mercadolibre.pens_luis_bootcamp_final.util.OrderMapper;
import com.mercadolibre.pens_luis_bootcamp_final.util.OrderStatusMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class OrderStatusMapperTest {

    private OrderStatusMapper statusMapper;

    private OrderMapper mapper;

    @BeforeEach
    void setUp() {
        statusMapper = new OrderStatusMapper();
        mapper = new OrderMapper();
    }

    @Test
    @DisplayName("Maps order to orderStatus")
    void mapOrderStatus() {
        OrderStatusDto expected = OrderFixture.defaultOrderStatusDto1();
        OrderStatusDto actual = statusMapper.map(OrderFixture.defaultOrder1());
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Adds zeros to string code")
    void convertCodeToString() {
        String expected = "000089327";
        String actual = statusMapper.intToCodeString(9, "89327");
        assertEquals(expected, actual);
    }

}