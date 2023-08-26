package org.btg.mapper;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.btg.dto.OrderRequest;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Objects;

import static org.btg.constant.OrderConstants.ITEM_ID;
import static org.btg.constant.OrderConstants.ITEM_ID_2;
import static org.btg.fixtures.OrderEntityFixture.createOrderEntityWithTwoItems;
import static org.btg.fixtures.OrderEntityFixture.createOrderEntityWithNoTotalPrice;
import static org.btg.fixtures.OrderFixture.createOrderWithTwoItemsDTO;
import static org.btg.fixtures.OrderItemEntityFixture.createOrderItemEntity;
import static org.btg.fixtures.OrderItemFixture.createOrderItemDTO;
import static org.btg.fixtures.OrderItemRequestFixture.createOrderItemRequest;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class OrderMapperTest {

    @Inject
    private OrderMapper orderMapper;

    @Test
    void toEntity() {

        var gatoradeItemRequest = createOrderItemRequest("Gatorade");

        var cocaItemRequest = createOrderItemRequest("Coca-Cola");

        var orderRequest = new OrderRequest()
                .setOrderId(1L)
                .setClientId(1L)
                .setItems(List.of(gatoradeItemRequest, cocaItemRequest));

        var expectedResponse = createOrderEntityWithNoTotalPrice();

        var response = orderMapper.toEntity(orderRequest);

        assertEquals(expectedResponse.getId(), response.getId());
        assertEquals(expectedResponse.getClientId(), response.getClientId());
        assertEquals(expectedResponse.getItems().size(), response.getItems().size());


    }

    @Test
    void toOrderItemEntity() {
        var orderItemRequest = createOrderItemRequest("Gatorade");

        var expectedResponse = createOrderItemEntity("Gatorade", null).setTotalPrice(null);

        var response = orderMapper.toOrderItemEntity(orderItemRequest, 1L);

        assertNotNull(response.getId().getOrderItemId());
        assertEquals(expectedResponse.getProductName(), response.getProductName());
        assertEquals(expectedResponse.getQuantity(), response.getQuantity());
        assertEquals(expectedResponse.getUnitPrice(), response.getUnitPrice());
        assertEquals(expectedResponse.getTotalPrice(), response.getTotalPrice());

    }

    @Test
    void toOrder() {
        var orderEntity = createOrderEntityWithTwoItems();

        var order = createOrderWithTwoItemsDTO();

        var response = orderMapper.toOrder(orderEntity);

        assertEquals(order, response);
    }


    @Test
    void toOrderItem() {
        var orderItemEntity = createOrderItemEntity("Gatorade", ITEM_ID);

        var expectedResponse  = createOrderItemDTO("Gatorade");

        var response = orderMapper.toOrderItem(orderItemEntity);

        assertEquals(expectedResponse, response);
    }

    @Test
    void toOrderItemEntityList() {
        var gatoradeItem = createOrderItemRequest("Gatorade");

        var cocaItem = createOrderItemRequest("Coca-Cola");

        var expectedResponse = List.of(
                createOrderItemEntity("Gatorade", ITEM_ID).setTotalPrice(null),
                createOrderItemEntity("Coca-Cola", ITEM_ID_2).setTotalPrice(null));

        var response = orderMapper.toOrderItemEntityList(List.of(gatoradeItem, cocaItem), 1L);

        response.forEach(r -> assertNotNull(r.getId().getOrderItemId()));
        assertTrue(response.stream().anyMatch(r -> Objects.equals(r.getProductName(), expectedResponse.get(0).getProductName())));
        assertTrue(response.stream().anyMatch(r -> Objects.equals(r.getProductName(), expectedResponse.get(1).getProductName())));

    }

    @Test
    void toOrdersList() {
        var orderEntity = createOrderEntityWithTwoItems();
        var order = createOrderWithTwoItemsDTO();

        var response = orderMapper.toOrdersList(List.of(orderEntity));

        assertEquals(List.of(order), response);
    }

    @Test
    void toOrderItemList() {
        var gatoradeItemEntity = createOrderItemEntity("Gatorade", ITEM_ID);
        var cocaItemEntity = createOrderItemEntity("Coca-Cola", ITEM_ID_2);

        var expectedResponse = List.of(createOrderItemDTO("Gatorade"), createOrderItemDTO("Coca-Cola"));
        var response = orderMapper.toOrderItemList(List.of(gatoradeItemEntity, cocaItemEntity));

        assertEquals(expectedResponse, response);
    }

}