package org.btg.repository;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.test.InjectMock;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.btg.dto.ClientOrderInfo;
import org.btg.dto.Order;
import org.btg.dto.OrderItem;
import org.btg.entity.OrderEntity;
import org.btg.entity.OrderItemEntity;
import org.btg.entity.OrderItemPK;
import org.btg.mapper.OrderMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@QuarkusTest
class OrderRepositoryTest {

    @Inject
    private OrderRepository repository;

    @InjectMock
    private OrderMapper orderMapper;


    @Test
    @Transactional
    void testReturnAllRequiredFields(){ //FIXME
        var item = new OrderItemEntity()
                .setId(new OrderItemPK(1L, 1L))
                .setTotalPrice(BigDecimal.valueOf(5L))
                .setProductName("Gatorade")
                .setUnitPrice(BigDecimal.ONE)
                .setQuantity(5)
                .setCreateAt(new Date())
                .setUpdateAt(new Date());

        var item2 = new OrderItemEntity()
                .setId(new OrderItemPK(2L, 1L))
                .setTotalPrice(BigDecimal.valueOf(5L))
                .setProductName("Coca-Cola")
                .setUnitPrice(BigDecimal.ONE)
                .setQuantity(5)
                .setCreateAt(new Date())
                .setUpdateAt(new Date());

        var orderEntity = new OrderEntity()
                .setId(1L)
                .setClientId(1L)
                .setTotalPrice(BigDecimal.TEN)
                .setItems(List.of(item, item2));

        PanacheEntityBase.persist(orderEntity);

        var gatoradeItem = new OrderItem()
                .setProductName("Gatorade")
                .setQuantity(5)
                .setUnitPrice(BigDecimal.ONE)
                .setTotalPrice(BigDecimal.valueOf(5L));

        var cocaItem = new OrderItem()
                .setProductName("Coca-Cola")
                .setQuantity(5)
                .setUnitPrice(BigDecimal.ONE)
                .setTotalPrice(BigDecimal.valueOf(5L));

        var order = new Order()
                .setOrderId(1L)
                .setTotalPrice(BigDecimal.TEN)
                .setItems(List.of(gatoradeItem, cocaItem));


        var expectedResponse = new ClientOrderInfo()
                .setClientId(1L)
                .setAmountOfOrders(2)
                .setOrders(List.of(order));

        Mockito.when(orderMapper.toOrder(Mockito.any(OrderEntity.class)))
                .thenReturn(order);

        var response = repository.findClientsOrders();

        Assertions.assertFalse(response.isEmpty());
//        Assertions.assertEquals(List.of(expectedResponse), response);

    }

}