package org.btg.fixtures;

import org.btg.entity.OrderItemEntity;
import org.btg.entity.OrderItemPK;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

public class OrderItemEntityFixture {

    private OrderItemEntityFixture() {
        //Default constructor
    }

    public static OrderItemEntity createOrderItemEntity(String productName, UUID itemId) {
        return new OrderItemEntity()
                .setId(new OrderItemPK().setOrderItemId(itemId).setOrderId(1L))
                .setTotalPrice(BigDecimal.valueOf(5L))
                .setProductName(productName)
                .setUnitPrice(BigDecimal.ONE)
                .setQuantity(5)
                .setCreateAt(new Date())
                .setUpdateAt(new Date());
    }
}
