package org.btg.rabbitmq;


import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import org.btg.client.OrderRequestAPI;
import org.btg.dto.OrderRequest;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

@ApplicationScoped
public class OrderRequestProducer implements OrderRequestAPI {

    @Channel("order-request")
    Emitter<OrderRequest> emitter;

    @Override
    public void createOrder(OrderRequest order) {
        emitter.send(order);
        Log.infof("Order request %s sent", order.orderId);
    }
}
