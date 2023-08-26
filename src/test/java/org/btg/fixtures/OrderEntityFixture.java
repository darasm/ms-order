package org.btg.fixtures;

import org.btg.entity.OrderEntity;

import java.math.BigDecimal;
import java.util.List;

import static org.btg.constant.OrderConstants.ITEM_ID;
import static org.btg.constant.OrderConstants.ITEM_ID_2;
import static org.btg.fixtures.OrderItemEntityFixture.createOrderItemEntity;

public class OrderEntityFixture {


    private OrderEntityFixture() {
        // Default construct
    }

    public static OrderEntity createOrderEntityWithTwoItems(Long orderId) {
        var item = createOrderItemEntity("Gatorade", ITEM_ID, orderId);

        var item2 = createOrderItemEntity("Coca-Cola", ITEM_ID_2, orderId);

        return new OrderEntity()
                .setId(orderId)
                .setClientId(1L)
                .setTotalPrice(BigDecimal.TEN)
                .setItems(List.of(item, item2));
    }

    public static OrderEntity createOrderEntityWithOneItem(Long orderId) {
        var item = createOrderItemEntity("Gatorade", ITEM_ID, orderId);

        return new OrderEntity()
                .setId(orderId)
                .setClientId(1L)
                .setTotalPrice(BigDecimal.TEN)
                .setItems(List.of(item));
    }

    public static OrderEntity createOrderEntityWithNoTotalPrice() {
        var orderEntity = createOrderEntityWithTwoItems(1L);
        orderEntity.setTotalPrice(null);
        orderEntity.getItems().forEach(item -> item.setTotalPrice(null));
        return orderEntity;
    }
}
