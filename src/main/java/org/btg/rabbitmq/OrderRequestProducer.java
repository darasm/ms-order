package org.btg.rabbitmq;


import jakarta.enterprise.context.ApplicationScoped;
import org.btg.client.OrderRequestAPI;
import org.btg.client.dto.OrderRequest;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

@ApplicationScoped
public class OrderRequestProducer implements OrderRequestAPI {

    @Channel("order-request")
    Emitter<OrderRequest> emitter;

    @Override
    public void createOrder(OrderRequest order) {
        emitter.send(order);
    }
}
