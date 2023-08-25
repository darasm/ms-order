package org.btg.dto;

import io.quarkus.runtime.annotations.RegisterForReflection;

import java.util.List;

@RegisterForReflection
public class OrderRequest {

    public Long orderId;
    public Long clientId;
    public List<OrderItemsRequest> items;

    public OrderRequest(Long orderId, Long clientId, List<OrderItemsRequest> items) {
        this.orderId = orderId;
        this.clientId = clientId;
        this.items = items;
    }

    public OrderRequest() {
    }

    public Long getOrderId() {
        return orderId;
    }

    public OrderRequest setOrderId(Long orderId) {
        this.orderId = orderId;
        return this;
    }

    public Long getClientId() {
        return clientId;
    }

    public OrderRequest setClientId(Long clientId) {
        this.clientId = clientId;
        return this;
    }

    public List<OrderItemsRequest> getItems() {
        return items;
    }

    public OrderRequest setItems(List<OrderItemsRequest> items) {
        this.items = items;
        return this;
    }

    @Override
    public String toString() {
        return "OrderProcessor{" +
                "orderId=" + orderId +
                ", clientId=" + clientId +
                ", items=" + items +
                '}';
    }
}
