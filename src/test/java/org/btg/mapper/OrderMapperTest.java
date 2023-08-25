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

@QuarkusTest
class OrderMapperTest {

    @Inject
    private OrderMapper orderMapper;

//    @Test
    void toEntity() { //FIXME

        var gatoradeItemRequest = new OrderItemsRequest()
                .setProductName("Gatorade")
                .setQuantity(5)
                .setPrice(BigDecimal.ONE);

        var cocaItemRequest = new OrderItemsRequest()
                .setProductName("Coca-Cola")
                .setQuantity(5)
                .setPrice(BigDecimal.ONE);


        var orderRequest = new OrderRequest()
                .setOrderId(1L)
                .setClientId(1L)
                .setItems(List.of(gatoradeItemRequest, cocaItemRequest));

        var expectedResponse = createOrderEntityWithNoTotalPrice();

        var response = orderMapper.toEntity(orderRequest);

        Assertions.assertEquals(expectedResponse, response);
    }

    @Test
    void toOrderItemEntity() {
        var orderItemRequest = createOrderItemRequest("Gatorade");

        var expectedResponse = createOrderItemEntity("Gatorade").setTotalPrice(null);

        var response = orderMapper.toOrderItemEntity(orderItemRequest, 1L);

        Assertions.assertEquals(expectedResponse, response);
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
        var orderItemEntity = new OrderItemEntity()
                .setId(new OrderItemPK(1L, 1L))
                .setQuantity(5)
                .setProductName("Gatorade")
                .setUnitPrice(BigDecimal.ONE)
                .setTotalPrice(BigDecimal.valueOf(5));

        var expectedResponse  = createOrderItemDTO("Gatorade");

        var response = orderMapper.toOrderItem(orderItemEntity);

        Assertions.assertEquals(expectedResponse, response);
    }

    @Test
    void toOrderItemEntityList() {
        var gatoradeItem = createOrderItemRequest("Gatorade");

        var cocaItem = createOrderItemRequest("Coca-Cola");

        var expectedResponse = List.of(
                createOrderItemEntity("Gatorade").setTotalPrice(null),
                createOrderItemEntity("Coca-Cola").setTotalPrice(null));

        var response = orderMapper.toOrderItemEntityList(List.of(gatoradeItem, cocaItem), 1L);

        Assertions.assertEquals(expectedResponse, response);

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
        var gatoradeItemEntity = createOrderItemEntity("Gatorade");
        var cocaItemEntity = createOrderItemEntity("Coca-Cola");

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
    private OrderItemEntity createOrderItemEntity(String productName) {
        return new OrderItemEntity()
                .setId(new OrderItemPK(1L, 1L))
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
        var item = createOrderItemEntity("Gatorade");

        var item2 = createOrderItemEntity("Coca-Cola");

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