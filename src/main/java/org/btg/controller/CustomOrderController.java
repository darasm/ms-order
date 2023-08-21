package org.btg.controller;

import jakarta.ws.rs.core.Response;
import org.btg.client.CustomOrderAPI;

public class CustomOrderController implements CustomOrderAPI {

    @Override
    public Response getClientOrders(Integer clientId) {
        return CustomOrderAPI.super.getClientOrders(clientId);
    }
}
