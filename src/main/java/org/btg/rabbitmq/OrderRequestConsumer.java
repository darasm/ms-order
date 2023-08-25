package org.btg.rabbitmq;

import io.quarkus.logging.Log;
import io.smallrye.common.annotation.Blocking;
import io.smallrye.reactive.messaging.rabbitmq.IncomingRabbitMQMessage;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.btg.client.dto.OrderItemsRequest;
import org.btg.client.dto.OrderRequest;
import org.btg.mapper.OrderMapper;
import org.btg.repository.OrderRepository;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Message;
import io.vertx.core.json.JsonObject;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.CompletionStage;

@ApplicationScoped
public class OrderRequestConsumer {

    private final OrderRepository repository;

    private final OrderMapper mapper;


    public OrderRequestConsumer(OrderRepository repository, OrderMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Incoming("requests")
    @Transactional
    @Blocking
    public CompletionStage<Void> persistOrderRequest(Message<OrderRequest> orderMessage) {
        Log.info("New message received");
        var order = ((JsonObject) ((IncomingRabbitMQMessage) orderMessage).getPayload()).mapTo((OrderRequest.class));
        var orderEntity = mapper.toEntity(order);
        orderEntity.setTotalPrice(calculateTotalPrice(order.items));
        repository.persist(orderEntity);
        Log.infof("Order %s persisted", orderEntity.getId());
        return orderMessage.ack();
    }

    private BigDecimal calculateTotalPrice(List<OrderItemsRequest> items) {
        return items.stream()
                .map(item -> item.getPrice()
                        .multiply(BigDecimal.valueOf(item.quantity)))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }


}
