package org.btg.controller;

import jakarta.ws.rs.core.Response;
import org.btg.client.CustomOrderAPI;
import org.btg.client.dto.ClientOrderInfo;

import java.util.List;

public class CustomOrderController implements CustomOrderAPI {
    @Override
    public List<ClientOrderInfo> getClientOrders(Integer clientId) {
        return CustomOrderAPI.super.getClientOrders(clientId);
    }
}
