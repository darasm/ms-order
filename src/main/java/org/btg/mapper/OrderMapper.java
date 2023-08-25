package org.btg.mapper;

import org.btg.client.dto.Order;
import org.btg.client.dto.OrderItem;
import org.btg.client.dto.OrderItemsRequest;
import org.btg.client.dto.OrderRequest;
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
    @Mapping(target = "id.orderItemId", source = "orderId")
    @Mapping(target = "unitPrice", source = "request.price")
    @Mapping(target = "createAt", ignore = true)
    @Mapping(target = "updateAt", ignore = true)
    OrderItemEntity toOrderItemEntity(OrderItemsRequest request, Long orderId);

    default List<OrderItemEntity> toOrderItemEntityList(List<OrderItemsRequest> entity, Long orderId){
        return entity.stream().map(o -> toOrderItemEntity(o, orderId)).toList();
    }

    @Mapping(target = "orderId", source = "orderEntity.id")
    @Mapping(target = "items", expression = "java(toOrderItemList(orderEntity.getItems()))")
    Order toOrder(OrderEntity orderEntity);
    List<Order> toOrders(List<OrderEntity> orderEntity);

    OrderItem toOrderItem(OrderItemEntity orderItemEntity);

    default List<OrderItem> toOrderItemList(List<OrderItemEntity> entity){
        return entity.stream().map(this::toOrderItem).toList();
    }

}
