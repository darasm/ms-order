package org.btg.fixtures;

import org.btg.entity.OrderEntity;
import org.btg.entity.OrderItemEntity;

import java.math.BigDecimal;
import java.util.List;

public class OrderEntityFixture {

    private OrderEntityFixture() {
        // Default construct
    }

    public static OrderEntity createOrderEntity(List<OrderItemEntity> items) {

        return new OrderEntity()
                .setId(1L)
                .setClientId(1L)
                .setTotalPrice(BigDecimal.TEN)
                .setItems(items);
    }

    public static OrderEntity createOrderEntityWithNoTotalPrice(List<OrderItemEntity> items) {
        return createOrderEntity(items).setTotalPrice(null);
    }
}
