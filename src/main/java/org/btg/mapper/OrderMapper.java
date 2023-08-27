package org.btg.mapper;

import org.btg.dto.Order;
import org.btg.dto.OrderItem;
import org.btg.dto.OrderItemsRequest;
import org.btg.dto.OrderRequest;
import org.btg.entity.OrderEntity;
import org.btg.entity.OrderItemEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "cdi")
public interface OrderMapper {

    @Mapping(target = "totalPrice", ignore = true)
    @Mapping(target = "createAt", ignore = true)
    @Mapping(target = "updateAt", ignore = true)
    @Mapping(target = "items", expression = "java(toOrderItemEntityList(request.items, request.orderId))")
    @Mapping(target = "id", source = "request.orderId")
    OrderEntity toEntity(OrderRequest request);

    @Mapping(target = "id.orderId", source = "orderId")
    @Mapping(target = "id.orderItemId", ignore = true)
    @Mapping(target = "unitPrice", source = "request.price")
    @Mapping(target = "createAt", ignore = true)
    @Mapping(target = "updateAt", ignore = true)
    OrderItemEntity toOrderItemEntity(OrderItemsRequest request, Long orderId);

    @Mapping(target = "orderId", source = "orderEntity.id")
    @Mapping(target = "items", expression = "java(toOrderItemList(orderEntity.getItems()))")
    Order toOrder(OrderEntity orderEntity);


    @Mapping(target = "totalPrice", source = "orderItemEntity.totalPrice")
    OrderItem toOrderItem(OrderItemEntity orderItemEntity);

    default List<OrderItemEntity> toOrderItemEntityList(List<OrderItemsRequest> entity, Long orderId) {
        return entity.stream().map(o -> toOrderItemEntity(o, orderId)).toList();
    }

    default List<Order> toOrdersList(List<OrderEntity> orderEntity) {
        return orderEntity.stream().map(this::toOrder).toList();
    }

    default List<OrderItem> toOrderItemList(List<OrderItemEntity> entity) {
        return entity.stream().map(this::toOrderItem).toList();
    }

}
