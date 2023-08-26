package org.btg.fixtures;

import org.btg.dto.OrderItem;

import java.math.BigDecimal;

public class OrderItemFixture {

    private OrderItemFixture() {
        /// Default constructor
    }

    public static OrderItem createOrderItemDTO(String productName) {
        return new OrderItem()
                .setProductName(productName)
                .setQuantity(5)
                .setUnitPrice(BigDecimal.ONE)
                .setTotalPrice(BigDecimal.valueOf(5L));
    }
}
