package org.btg.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import org.btg.dto.ClientOrderInfo;
import org.btg.dto.PageInfo;
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

        PaginationInfo resultPaginationInfo = new PaginationInfo();
        resultPaginationInfo.setPage(orders.page().index + 1);
        resultPaginationInfo.setPageSize(orders.page().size);
        resultPaginationInfo.setCount(orders.count());

        return new PaginatedResponse<>(clientOrders, resultPaginationInfo);

    }
}
