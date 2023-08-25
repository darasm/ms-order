package org.btg.controller;

import io.quarkus.test.InjectMock;
import io.quarkus.test.Mock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.btg.dto.ClientOrderInfo;
import org.btg.dto.Order;
import org.btg.dto.OrderItem;
import org.btg.repository.OrderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.List;

@QuarkusTest
class CustomOrderControllerTest {

    @Inject
    private CustomOrderController controller;

    @InjectMock
    private OrderRepository repository;

    @Test
    void testReturnEmptyClientOrderInfo(){
        Mockito.when(repository.findClientsOrders())
                .thenReturn(List.of());

        var response = controller.getClientOrders();

        Assertions.assertTrue(response.isEmpty());
    }

    @Test
    void testReturnClientOrderInfo(){
        var gatoradeItem = new OrderItem()
                .setProductName("Gatorade")
                .setQuantity(5)
                .setUnitPrice(BigDecimal.ONE)
                .setTotalPrice(BigDecimal.valueOf(5L));

        var cocaItem = new OrderItem()
                .setProductName("Coca-Cola")
                .setQuantity(5)
                .setUnitPrice(BigDecimal.ONE)
                .setTotalPrice(BigDecimal.valueOf(5L));

        var order = new Order()
                .setOrderId(1L)
                .setTotalPrice(BigDecimal.TEN)
                .setItems(List.of(gatoradeItem, cocaItem));

        var expectedReturn = new ClientOrderInfo();
        expectedReturn.setClientId(1L);
        expectedReturn.setAmountOfOrders(2);
        expectedReturn.setOrders(List.of());


        Mockito.when(repository.findClientsOrders())
                .thenReturn(List.of(expectedReturn));

        var response = controller.getClientOrders();

        Assertions.assertFalse(response.isEmpty());
    }

}