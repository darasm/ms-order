package org.btg.fixtures;

import org.btg.dto.ClientOrderInfo;

import java.util.List;

import static org.btg.fixtures.OrderFixture.createOrderIdTwoWithOneItemDTO;
import static org.btg.fixtures.OrderFixture.createOrderWithTwoItemsDTO;

public class ClientOrderInfoFixture {

    private ClientOrderInfoFixture() {
        //Default constructor
    }

    public static ClientOrderInfo createClientOrderInfoWithTwoItems(){

        var order = createOrderWithTwoItemsDTO();
        var secondOrder = createOrderIdTwoWithOneItemDTO();

        return new ClientOrderInfo()
                .setClientId(1L)
                .setAmountOfOrders(2)
                .setOrders(List.of(order, secondOrder));
    }
}
