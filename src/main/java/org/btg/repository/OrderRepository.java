package org.btg.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Page;
import jakarta.enterprise.context.ApplicationScoped;
import org.btg.dto.ClientOrderInfo;
import org.btg.dto.PageInfo;
import org.btg.dto.PaginatedResponse;
import org.btg.dto.PaginationInfo;
import org.btg.entity.OrderEntity;
import org.btg.mapper.OrderMapper;
import org.btg.mapper.PaginationMapper;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class OrderRepository implements PanacheRepositoryBase<OrderEntity, Long> {

    private final OrderMapper mapper;
    private final PaginationMapper paginationMapper;

    public OrderRepository(OrderMapper mapper, PaginationMapper paginationMapper) {
        this.mapper = mapper;
        this.paginationMapper = paginationMapper;
    }

    public PaginatedResponse<List<ClientOrderInfo>> findClientsOrders(PageInfo paginationInfo) {

        var page = Page.ofSize(paginationInfo.getPageSize()).index(paginationInfo.getPage() -1);

        var orders = findAll().page(page);

        var clientOrders =  orders.list().stream()
                .collect(Collectors.groupingBy(OrderEntity::getClientId)).entrySet()
                .stream().map(c ->
                new ClientOrderInfo()
                        .setClientId(c.getKey())
                        .setAmountOfOrders(c.getValue().size())
                        .setOrders(mapper.toOrdersList(c.getValue()))).toList();

        var resultPaginationInfo = paginationMapper.from(orders);

        return new PaginatedResponse<>(clientOrders, resultPaginationInfo);

    }
}
