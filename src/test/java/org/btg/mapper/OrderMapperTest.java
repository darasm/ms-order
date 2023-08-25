package org.btg.mapper;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.btg.dto.Order;
import org.btg.dto.OrderItem;
import org.btg.dto.OrderItemsRequest;
import org.btg.dto.OrderRequest;
import org.btg.entity.OrderEntity;
import org.btg.entity.OrderItemEntity;
import org.btg.entity.OrderItemPK;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@QuarkusTest
class OrderMapperTest {

    @Inject
    private OrderMapper orderMapper;

    private static final UUID ITEM_ID = UUID.fromString("834efdb6-6044-4b44-8fcb-560710936f37");
    private static final UUID ITEM_ID_2 = UUID.fromString("511fea83-9f5f-4606-85ec-3d769da4bf63");

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

    private OrderItemsRequest createOrderItemRequest(String productName) {
        return new OrderItemsRequest()
                .setProductName(productName)
                .setPrice(BigDecimal.ONE)
                .setQuantity(5);
    }
    private OrderItemEntity createOrderItemEntity(String productName, UUID itemId) {
        return new OrderItemEntity()
                .setId(new OrderItemPK().setOrderItemId(itemId).setOrderId(1L))
                .setTotalPrice(BigDecimal.valueOf(5L))
                .setProductName(productName)
                .setUnitPrice(BigDecimal.ONE)
                .setQuantity(5)
                .setCreateAt(new Date())
                .setUpdateAt(new Date());
    }

    private OrderItem createOrderItemDTO(String productName) {
        return new OrderItem()
                .setProductName(productName)
                .setQuantity(5)
                .setUnitPrice(BigDecimal.ONE)
                .setTotalPrice(BigDecimal.valueOf(5L));
    }

    private Order createOrderDTO() {
        var gatoradeItem = createOrderItemDTO("Gatorade");

        var cocaItem = createOrderItemDTO("Coca-Cola");

        return new Order()
                .setOrderId(1L)
                .setTotalPrice(BigDecimal.TEN)
                .setItems(List.of(gatoradeItem, cocaItem));
    }

    private OrderEntity createOrderEntity() {
        var item = createOrderItemEntity("Gatorade", ITEM_ID);

        var item2 = createOrderItemEntity("Coca-Cola", ITEM_ID_2);

        return new OrderEntity()
                .setId(1L)
                .setClientId(1L)
                .setTotalPrice(BigDecimal.TEN)
                .setItems(List.of(item, item2));
    }

    private OrderEntity createOrderEntityWithNoTotalPrice() {
        var orderEntity = createOrderEntity();
        orderEntity.setTotalPrice(null);
        orderEntity.getItems().forEach(item -> item.setTotalPrice(null));
        return orderEntity;
    }
}