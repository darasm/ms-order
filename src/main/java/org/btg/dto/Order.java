package org.btg.dto;

import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(orderId, order.orderId) && Objects.equals(totalPrice, order.totalPrice) && Objects.equals(items, order.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, totalPrice, items);
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", totalPrice=" + totalPrice +
                ", items=" + items +
                '}';
    }
}
