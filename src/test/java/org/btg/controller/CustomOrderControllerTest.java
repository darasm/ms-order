package org.btg.controller;

import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.btg.dto.ClientOrderInfo;
import org.btg.repository.OrderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

@QuarkusTest
class CustomOrderControllerTest {

    @Inject
    private CustomOrderController controller;

    @InjectMock
    private OrderRepository repository;

    @Test
    void testReturnEmptyClientOrderInfo() {
        Mockito.when(repository.findClientsOrders())
                .thenReturn(List.of());

        var response = controller.getClientOrders();

        Assertions.assertTrue(response.isEmpty());
    }

    @Test
    void testReturnClientOrderInfo() {

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