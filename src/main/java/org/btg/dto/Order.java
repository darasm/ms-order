package org.btg.dto;

import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.math.BigDecimal;
import java.util.List;

public class Order {
    @Schema(title = "Order Identifier", example = "1", minimum = "0")
    private Long orderId;
    @Schema(title = "Total Order Value", example = "150.50")
    private BigDecimal totalPrice;
    @Schema(title = "Order Items Information List", type = SchemaType.ARRAY)
    private List<OrderItem> items;

    public Order() {
        //Default constructor
    }

    public Long getOrderId() {
        return orderId;
    }

    public Order setOrderId(Long orderId) {
        this.orderId = orderId;
        return this;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public Order setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
        return this;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public Order setItems(List<OrderItem> items) {
        this.items = items;
        return this;
    }
}
