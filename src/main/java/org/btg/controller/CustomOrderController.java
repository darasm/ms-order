package org.btg.controller;

import org.btg.client.CustomOrderAPI;
import org.btg.client.dto.ClientOrderInfo;

import java.util.List;

public class CustomOrderController implements CustomOrderAPI {
    @Override
    public List<ClientOrderInfo> getClientOrders() {
        return CustomOrderAPI.super.getClientOrders();
    }
}
