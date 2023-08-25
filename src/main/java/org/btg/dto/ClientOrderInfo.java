package org.btg.dto;

import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.util.List;
import java.util.Objects;

public class ClientOrderInfo {

    @Schema(title = "Client Identifier", example = "1")
    private Long clientId;
    @Schema(title = "Client Identifier", example = "1", type = SchemaType.INTEGER)
    private Integer amountOfOrders;
    @Schema(title = "Order Information list", type = SchemaType.ARRAY)
    private List<Order> orders;

    public ClientOrderInfo() {
        //Default constructor
    }

    public Long getClientId() {
        return clientId;
    }

    public ClientOrderInfo setClientId(Long clientId) {
        this.clientId = clientId;
        return this;
    }

    public Integer getAmountOfOrders() {
        return amountOfOrders;
    }

    public ClientOrderInfo setAmountOfOrders(Integer amountOfOrders) {
        this.amountOfOrders = amountOfOrders;
        return this;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public ClientOrderInfo setOrders(List<Order> orders) {
        this.orders = orders;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientOrderInfo that = (ClientOrderInfo) o;
        return Objects.equals(clientId, that.clientId) && Objects.equals(amountOfOrders, that.amountOfOrders) && Objects.equals(orders, that.orders);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientId, amountOfOrders, orders);
    }

    @Override
    public String toString() {
        return "ClientOrderInfo{" +
                "clientId=" + clientId +
                ", amountOfOrders=" + amountOfOrders +
                ", orders=" + orders +
                '}';
    }
}
