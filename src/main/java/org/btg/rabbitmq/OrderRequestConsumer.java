package org.btg.rabbitmq;

import io.smallrye.common.annotation.Blocking;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.btg.client.dto.OrderRequest;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Message;

import java.util.concurrent.CompletionStage;

@ApplicationScoped
public class OrderRequestConsumer {

    @Incoming("requests")
    @Transactional
    @Blocking
    public CompletionStage<Void> persistOrderRequest(Message<OrderRequest> orderMessage) {
        return orderMessage.ack();
    }


}
