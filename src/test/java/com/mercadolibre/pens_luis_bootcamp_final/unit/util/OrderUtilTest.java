package com.mercadolibre.pens_luis_bootcamp_final.unit.util;

import com.mercadolibre.pens_luis_bootcamp_final.models.Order;
import com.mercadolibre.pens_luis_bootcamp_final.repositories.PartRepository;
import com.mercadolibre.pens_luis_bootcamp_final.unit.fixtures.OrderFixture;
import com.mercadolibre.pens_luis_bootcamp_final.util.MockitoExtension;
import com.mercadolibre.pens_luis_bootcamp_final.util.OrderUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class OrderUtilTest {

    private OrderUtil orderUtil;
    private PartRepository repoPartsMock;

    @BeforeEach
    void setUp() {
        repoPartsMock = Mockito.mock(PartRepository.class);
        orderUtil = new OrderUtil(repoPartsMock);
    }

    @Test
    @DisplayName("Generate order")
    void generateOrder() {
        Order expected = OrderFixture.defaultOrder();
        Order actual = orderUtil.generateOrder(OrderFixture.defaultConcessionarie(),
                OrderFixture.defaultShippingType(), OrderFixture.defaultCentralHouse(),
                OrderFixture.defaultDeliveryStatus(), 3);
        assertEquals(expected, actual);
    }




}