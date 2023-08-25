package org.btg.mapper;

import org.btg.client.dto.OrderRequest;
import org.btg.entity.OrderEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi")
public interface OrderMapper {

    OrderEntity toEntity(OrderRequest request);
}
