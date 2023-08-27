package org.btg.mapper;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import org.btg.dto.PaginationInfo;
import org.btg.entity.OrderEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "cdi")
public interface PaginationMapper {

    @Mapping(target = "page", expression = "java(orders.page().index + 1)")
    @Mapping(target = "pageSize", expression = "java(orders.page().size)")
    @Mapping(target = "count", expression = "java(orders.count())")
    PaginationInfo from(PanacheQuery<OrderEntity> orders);
}
