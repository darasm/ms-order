package org.btg.client.dto;

import java.math.BigDecimal;
import java.util.List;

public class Order {
    private Long orderId;
    private BigDecimal totalPrice;
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
