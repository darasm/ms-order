package org.btg.fixtures;

import org.btg.dto.Order;

import java.math.BigDecimal;
import java.util.List;

import static org.btg.fixtures.OrderItemFixture.createOrderItemDTO;

public class OrderFixture {

    private OrderFixture() {
        // Default construct
    }

    public static Order createOrderWithTwoItemsDTO() {
        var gatoradeItem = createOrderItemDTO("Gatorade");

        var cocaItem = createOrderItemDTO("Coca-Cola");

        return new Order()
                .setOrderId(1L)
                .setTotalPrice(BigDecimal.TEN)
                .setItems(List.of(gatoradeItem, cocaItem));
    }

    public static Order createOrderWithOneItemDTO() {
        var cocaItem = createOrderItemDTO("Coca-Cola");

        return new Order()
                .setOrderId(1L)
                .setTotalPrice(BigDecimal.TEN)
                .setItems(List.of(cocaItem));
    }
}
