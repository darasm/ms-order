package org.btg.client.dto;

import java.util.List;

public class ClientOrderInfo {

    private Integer clientId;
    private Integer amountOfOrders;
    private List<Order> orders;

    public ClientOrderInfo() {
        //Default constructor
    }
}
