package org.btg.mapper;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.btg.dto.OrderRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.btg.constant.OrderConstants.ITEM_ID;
import static org.btg.constant.OrderConstants.ITEM_ID_2;
import static org.btg.fixtures.OrderEntityFixture.createOrderEntity;
import static org.btg.fixtures.OrderEntityFixture.createOrderEntityWithNoTotalPrice;
import static org.btg.fixtures.OrderFixture.createOrderDTO;
import static org.btg.fixtures.OrderItemEntityFixture.createOrderItemEntity;
import static org.btg.fixtures.OrderItemFixture.createOrderItemDTO;
import static org.btg.fixtures.OrderItemRequestFixture.createOrderItemRequest;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

        Assertions.assertEquals(expectedResponse.getId(), response.getId());
        Assertions.assertEquals(expectedResponse.getClientId(), response.getClientId());
        Assertions.assertEquals(expectedResponse.getItems().size(), response.getItems().size());


    }

    @Test
    void toOrderItemEntity() {
        var orderItemRequest = createOrderItemRequest("Gatorade");

        var expectedResponse = createOrderItemEntity("Gatorade", null).setTotalPrice(null);

        var response = orderMapper.toOrderItemEntity(orderItemRequest, 1L);

        assertNotNull(response.getId().getOrderItemId());
        Assertions.assertEquals(expectedResponse.getProductName(), response.getProductName());
        Assertions.assertEquals(expectedResponse.getQuantity(), response.getQuantity());
        Assertions.assertEquals(expectedResponse.getUnitPrice(), response.getUnitPrice());
        Assertions.assertEquals(expectedResponse.getTotalPrice(), response.getTotalPrice());

    }

    @Test
    void toOrder() {
        var orderEntity = createOrderEntity();

        var order = createOrderDTO();

        var response = orderMapper.toOrder(orderEntity);

        Assertions.assertEquals(order, response);
    }


    @Test
    void toOrderItem() {
        var orderItemEntity = createOrderItemEntity("Gatorade", ITEM_ID);

        var expectedResponse  = createOrderItemDTO("Gatorade");

        var response = orderMapper.toOrderItem(orderItemEntity);

        Assertions.assertEquals(expectedResponse, response);
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
        assertTrue(response.stream().anyMatch(r -> r.getProductName() == expectedResponse.get(0).getProductName()));
        assertTrue(response.stream().anyMatch(r -> r.getProductName() == expectedResponse.get(1).getProductName()));

    }

    @Test
    void toOrdersList() {
        var orderEntity = createOrderEntity();
        var order = createOrderDTO();

        var response = orderMapper.toOrdersList(List.of(orderEntity));

        Assertions.assertEquals(List.of(order), response);
    }

    @Test
    void toOrderItemList() {
        var gatoradeItemEntity = createOrderItemEntity("Gatorade", ITEM_ID);
        var cocaItemEntity = createOrderItemEntity("Coca-Cola", ITEM_ID_2);

        var expectedResponse = List.of(createOrderItemDTO("Gatorade"), createOrderItemDTO("Coca-Cola"));
        var response = orderMapper.toOrderItemList(List.of(gatoradeItemEntity, cocaItemEntity));

        Assertions.assertEquals(expectedResponse, response);
    }

}