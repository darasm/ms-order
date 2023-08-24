package org.btg.client.dto;

import io.quarkus.runtime.annotations.RegisterForReflection;

import java.util.List;

@RegisterForReflection
public class OrderProcessor {

    public Integer orderId;
    public Integer clientId;
    public List<OrderItemsProcessor> items;

    public OrderProcessor(Integer orderId, Integer clientId, List<OrderItemsProcessor> items) {
        this.orderId = orderId;
        this.clientId = clientId;
        this.items = items;
    }

    public OrderProcessor() {
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
