package org.btg.controller;

import io.quarkus.test.InjectMock;
import io.quarkus.test.Mock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.btg.dto.ClientOrderInfo;
import org.btg.dto.PaginatedResponse;
import org.btg.dto.PaginationInfo;
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
    void testReturnEmptyClientOrderInfo() { //FIXME
        Mockito.when(repository.findClientsOrders(Mockito.any(PaginationInfo.class)))
                .thenReturn(new PaginatedResponse<>(List.of(), new PaginationInfo()));

        var response = controller.getClientOrders(new PaginationInfo());

        Assertions.assertTrue(response.getContent().isEmpty());
    }

    @Test
    void testReturnClientOrderInfo() { //FIXME

        var expectedReturn = new ClientOrderInfo();
        expectedReturn.setClientId(1L);
        expectedReturn.setAmountOfOrders(2);
        expectedReturn.setOrders(List.of());


        Mockito.when(repository.findClientsOrders(Mockito.any(PaginationInfo.class)))
                .thenReturn(new PaginatedResponse<>(List.of(expectedReturn), new PaginationInfo()));

        var response = controller.getClientOrders(new PaginationInfo());

        Assertions.assertFalse(response.getContent().isEmpty());
    }

}