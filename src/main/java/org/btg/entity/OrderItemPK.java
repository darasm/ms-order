package org.btg.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class OrderItemPK implements Serializable {

    @Column(name = "order_item_id")
    private Long orderItemId;
    @Column(name = "order_id")
    private Long orderId;

    public OrderItemPK() {
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
}
