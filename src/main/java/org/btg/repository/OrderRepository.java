package org.btg.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import org.btg.client.dto.ClientOrderInfo;
import org.btg.entity.OrderEntity;
import org.btg.mapper.OrderMapper;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class OrderRepository implements PanacheRepositoryBase<OrderEntity, Long> {

    private final OrderMapper mapper;

    public OrderRepository(OrderMapper mapper) {
        this.mapper = mapper;
    }

    public List<ClientOrderInfo> findClientsOrders() {
        var orders = this.findAll().stream()
                .collect(Collectors.groupingBy(OrderEntity::getClientId))
                .entrySet();

        var orderInt = orders.stream().map(c ->
                new ClientOrderInfo()
                        .setClientId(c.getKey())
                        .setAmountOfOrders(c.getValue().size())
                        .setOrders(mapper.toOrders(c.getValue())))
                .toList();

        return orderInt;
    }
}
