package org.btg.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import org.btg.dto.ClientOrderInfo;
import org.btg.dto.PaginatedResponse;
import org.btg.dto.PaginationInfo;
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

    public PaginatedResponse<List<ClientOrderInfo>> findClientsOrders(PaginationInfo paginationInfo) {
        var orders = this.findAll().stream()
                .collect(Collectors.groupingBy(OrderEntity::getClientId))
                .entrySet();

        var clientOrders =  orders.stream().map(c ->
                new ClientOrderInfo()
                        .setClientId(c.getKey())
                        .setAmountOfOrders(c.getValue().size())
                        .setOrders(mapper.toOrdersList(c.getValue()))).toList();

        return new PaginatedResponse<>(clientOrders, new PaginationInfo());

    }
}
