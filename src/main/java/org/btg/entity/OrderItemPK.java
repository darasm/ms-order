package org.btg.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class OrderItemPK implements Serializable {
    @Column(name = "order_item_id")
    private Long orderItemId;
    @Column(name = "order_id")
    private Long orderId;

    public OrderItemPK() {
        // Default constructor
    }

    public OrderItemPK(Long orderItemId, Long orderId) {
        this.orderItemId = orderItemId;
        this.orderId = orderId;
    }

    public Long getOrderItemId() {
        return orderItemId;
    }

    public OrderItemPK setOrderItemId(Long orderItemId) {
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
