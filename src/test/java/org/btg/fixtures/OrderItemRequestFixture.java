package org.btg.fixtures;

import org.btg.dto.OrderItemsRequest;

import java.math.BigDecimal;

public class OrderItemRequestFixture {

    private OrderItemRequestFixture() {
        // Default Constructor
    }

    public static OrderItemsRequest createOrderItemRequest(String productName) {
        return new OrderItemsRequest()
                .setProductName(productName)
                .setPrice(BigDecimal.ONE)
                .setQuantity(5);
    }
}
