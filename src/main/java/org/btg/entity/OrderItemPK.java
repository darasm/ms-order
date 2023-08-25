package org.btg.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Embeddable
public class OrderItemPK implements Serializable {
    @Column(name = "order_item_id", columnDefinition = "uuid")
    private UUID orderItemId = UUID.randomUUID();
    @Column(name = "order_id")
    private Long orderId;

    public OrderItemPK() {
        // Default constructor
    }

    public OrderItemPK(UUID orderItemId, Long orderId) {
        this.orderItemId = orderItemId == null ? UUID.randomUUID() : orderItemId;
        this.orderId = orderId;
    }

    public UUID getOrderItemId() {
        return orderItemId;
    }

    public OrderItemPK setOrderItemId(UUID orderItemId) {
        this.orderItemId = orderItemId;
        return this;
    }

    public Long getOrderId() {
        return orderId;
    }

    public OrderItemPK setOrderId(Long orderId) {
        this.orderId = orderId;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItemPK that = (OrderItemPK) o;
        return Objects.equals(orderItemId, that.orderItemId) && Objects.equals(orderId, that.orderId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderItemId, orderId);
    }

    @Override
    public String toString() {
        return "OrderItemPK{" +
                "orderItemId=" + orderItemId +
                ", orderId=" + orderId +
                '}';
    }
}
