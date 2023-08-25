package org.btg.mapper;

import org.btg.client.dto.OrderRequest;
import org.btg.entity.OrderEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "cdi")
public interface OrderMapper {

    @Mapping(target = "totalPrice", ignore = true)
    @Mapping(target = "createAt", ignore = true)
    @Mapping(target = "updateAt", ignore = true)
    @Mapping(target = "id", source = "request.orderId")
    OrderEntity toEntity(OrderRequest request);
}
