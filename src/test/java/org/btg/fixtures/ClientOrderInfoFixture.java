package org.btg.fixtures;

import org.btg.dto.ClientOrderInfo;

import java.util.List;

import static org.btg.fixtures.OrderFixture.createOrderIdTwoWithOneItemDTO;
import static org.btg.fixtures.OrderFixture.createOrderWithTwoItemsDTO;

public class ClientOrderInfoFixture {

    private ClientOrderInfoFixture() {
        //Default constructor
    }

    public static List<ClientOrderInfo> createClientOrderInfoWithTwoItems(){

        var order = createOrderWithTwoItemsDTO();
        var secondOrder = createOrderIdTwoWithOneItemDTO();

        return List.of(new ClientOrderInfo()
                .setClientId(1L)
                .setAmountOfOrders(2)
                .setOrders(List.of(order, secondOrder)));
    }

    public static List<ClientOrderInfo> createOrderForTwoClients(){
        var client1 = createClientOrderInfoWithTwoItems().get(0);
        var client2 = createClientOrderInfoWithTwoItems().get(0).setClientId(2L);

        return List.of(client1, client2);
    }
}
