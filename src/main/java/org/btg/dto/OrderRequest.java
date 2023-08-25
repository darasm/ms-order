package org.btg.dto;

import io.quarkus.runtime.annotations.RegisterForReflection;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.util.List;
import java.util.Objects;

@RegisterForReflection
public class OrderRequest {
    @Schema(title = "Order Identifier", example = "1", minimum = "0")
    public Long orderId;
    @Schema(title = "Client Identifier", example = "1", required = true, minimum = "0")
    public Long clientId;
    @Schema(title = "Order Information list", type = SchemaType.ARRAY, required = true, minLength = 1)
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderRequest that = (OrderRequest) o;
        return Objects.equals(orderId, that.orderId) && Objects.equals(clientId, that.clientId) && Objects.equals(items, that.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, clientId, items);
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
