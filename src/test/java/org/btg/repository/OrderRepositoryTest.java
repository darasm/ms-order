package org.btg.repository;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.btg.mapper.OrderMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.btg.fixtures.ClientOrderInfoFixture.createClientOrderInfoWithTwoItems;
import static org.btg.fixtures.ClientOrderInfoFixture.createOrderForTwoClients;
import static org.btg.fixtures.OrderEntityFixture.createOrderEntityWithOneItem;
import static org.btg.fixtures.OrderEntityFixture.createOrderEntityWithTwoItems;
import static org.btg.fixtures.OrderFixture.createOrderIdTwoWithOneItemDTO;
import static org.btg.fixtures.OrderFixture.createOrderWithTwoItemsDTO;

@QuarkusTest
class OrderRepositoryTest {

    @Inject
    private OrderRepository repository;

    @InjectMock
    private OrderMapper orderMapper;


    @Test
    @Transactional
    void testMultiplesClients() {

        var orderEntity = createOrderEntityWithTwoItems(1L);
        var secondItem = createOrderEntityWithOneItem(2L);

        var orderEntitySecondClient = createOrderEntityWithTwoItems(3L).setClientId(2L);
        var secondItemSecondClient = createOrderEntityWithOneItem(4L).setClientId(2L);

        PanacheEntityBase.persist(List.of(orderEntity, secondItem));
        PanacheEntityBase.persist(List.of(orderEntitySecondClient,secondItemSecondClient));


        var expectedResponse = createOrderForTwoClients();

        Mockito.when(orderMapper.toOrdersList(Mockito.any()))
                .thenReturn(List.of(createOrderWithTwoItemsDTO(),
                        createOrderIdTwoWithOneItemDTO()));

        var response = repository.findClientsOrders();

        Assertions.assertFalse(response.isEmpty());
        Assertions.assertEquals(expectedResponse, response);

    }

    @Test
    @Transactional
    void testClientWithMoreThanOnwOrder(){

        var orderEntity = createOrderEntityWithTwoItems(1L);

        var secondOrderEntity = createOrderEntityWithOneItem(2L);

        PanacheEntityBase.persist(orderEntity);
        PanacheEntityBase.persist(secondOrderEntity);

        var expectedResponse = createClientOrderInfoWithTwoItems();

        Mockito.when(orderMapper.toOrdersList(Mockito.any()))
                .thenReturn(List.of(createOrderWithTwoItemsDTO(),
                        createOrderIdTwoWithOneItemDTO()));

        var response = repository.findClientsOrders();

        Assertions.assertFalse(response.isEmpty());
        Assertions.assertEquals(expectedResponse, response);

    }

}