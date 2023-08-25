package org.btg.controller;

import org.btg.client.CustomOrderAPI;
import org.btg.dto.ClientOrderInfo;
import org.btg.repository.OrderRepository;

import java.util.List;

public class CustomOrderController implements CustomOrderAPI {
    private final OrderRepository repository;
    public CustomOrderController(OrderRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<ClientOrderInfo> getClientOrders() {
        return repository.findClientsOrders();
    }
}
