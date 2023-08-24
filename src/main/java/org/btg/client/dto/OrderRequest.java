package org.btg.client.dto;

import io.quarkus.runtime.annotations.RegisterForReflection;

import java.util.List;

@RegisterForReflection
public class OrderRequest {

    public Integer orderId;
    public Integer clientId;
    public List<OrderItemsRequest> items;

    public OrderRequest(Integer orderId, Integer clientId, List<OrderItemsRequest> items) {
        this.orderId = orderId;
        this.clientId = clientId;
        this.items = items;
    }

    public OrderRequest() {
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
