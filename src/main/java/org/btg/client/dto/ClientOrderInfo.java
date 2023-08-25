package org.btg.client.dto;

import java.util.List;

public class ClientOrderInfo {

    private Long clientId;
    private Integer amountOfOrders;
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
}
