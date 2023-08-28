package org.btg.controller;

import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.btg.dto.ClientOrderInfo;
import org.btg.dto.PageInfo;
import org.btg.dto.PaginatedResponse;
import org.btg.dto.PaginationInfo;
import org.btg.repository.OrderRepository;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.btg.fixtures.OrderFixture.createOrderIdTwoWithOneItemDTO;
import static org.btg.fixtures.OrderFixture.createOrderWithTwoItemsDTO;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@QuarkusTest
class CustomOrderControllerTest {

    @Inject
    private CustomOrderController controller;

    @InjectMock
    private OrderRepository repository;

    @Test
    void testReturnEmptyClientOrderInfo() {
        when(repository.findClientsOrders(any(PageInfo.class)))
                .thenReturn(new PaginatedResponse<>(List.of(), new PaginationInfo()));

        var response = controller.getClientOrders(new PageInfo());

        assertTrue(response.getContent().isEmpty());
        assertEquals(new PaginationInfo(), response.getPagination());
    }

    @Test
    void testReturnClientOrderInfo() {

        var expectedReturn = new ClientOrderInfo();
        expectedReturn.setClientId(1L);
        expectedReturn.setAmountOfOrders(2);
        expectedReturn.setOrders(List.of(createOrderWithTwoItemsDTO(), createOrderIdTwoWithOneItemDTO()));

        when(repository.findClientsOrders(any(PageInfo.class)))
                .thenReturn(new PaginatedResponse<>(List.of(expectedReturn), new PaginationInfo()));

        var response = controller.getClientOrders(new PageInfo());

        assertFalse(response.getContent().isEmpty());
        assertEquals(List.of(expectedReturn), response.getContent());
    }

}