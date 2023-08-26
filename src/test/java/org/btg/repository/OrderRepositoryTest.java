package org.btg.repository;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.btg.dto.ClientOrderInfo;
import org.btg.dto.Order;
import org.btg.dto.OrderItem;
import org.btg.mapper.OrderMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.btg.fixtures.OrderEntityFixture.createOrderEntityWithOneItem;
import static org.btg.fixtures.OrderEntityFixture.createOrderEntityWithTwoItems;
import static org.btg.fixtures.OrderFixture.createOrderIdTwoWithOneItemDTO;
import static org.btg.fixtures.OrderFixture.createOrderWithTwoItemsDTO;
import static org.btg.fixtures.OrderItemFixture.createOrderItemDTO;

@QuarkusTest
class OrderRepositoryTest {

    @Inject
    private OrderRepository repository;

    @InjectMock
    private OrderMapper orderMapper;

    private static final UUID ITEM_ID = UUID.fromString("834efdb6-6044-4b44-8fcb-560710936f37");
    private static final UUID ITEM_ID_2 = UUID.fromString("511fea83-9f5f-4606-85ec-3d769da4bf63");


    @Test
    @Transactional
    void testClientWithOnwOrder() {

        var orderEntity = createOrderEntityWithTwoItems();
        PanacheEntityBase.persist(orderEntity);

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


        var expectedResponse = new ClientOrderInfo()
                .setClientId(1L)
                .setAmountOfOrders(1)
                .setOrders(List.of(order));

        Mockito.when(orderMapper.toOrdersList(Mockito.any()))
                .thenReturn(List.of(order));

        var response = repository.findClientsOrders();

        Assertions.assertFalse(response.isEmpty());
        Assertions.assertEquals(List.of(expectedResponse), response);

    }

    @Test
    @Transactional
    void testClientWithMoreThanOnwOrder(){

        var orderEntity = createOrderEntityWithTwoItems();

        var secondOrderEntity = createOrderEntityWithOneItem();

        PanacheEntityBase.persist(orderEntity);
        PanacheEntityBase.persist(secondOrderEntity);


        var order = createOrderWithTwoItemsDTO();
        var secondOrder = createOrderIdTwoWithOneItemDTO();


        var expectedResponse = new ClientOrderInfo()
                .setClientId(1L)
                .setAmountOfOrders(2)
                .setOrders(List.of(order, secondOrder));

        Mockito.when(orderMapper.toOrdersList(Mockito.any()))
                .thenReturn(List.of(order, secondOrder));

        var response = repository.findClientsOrders();

        Assertions.assertFalse(response.isEmpty());
        Assertions.assertEquals(List.of(expectedResponse), response);

    }

}