package org.btg.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "order_item")
public class OrderItemEntity extends PanacheEntityBase {

    @EmbeddedId
    private OrderItemPK id;


    public OrderItemEntity() {
    }

    public OrderItemEntity(OrderItemPK id) {
        this.id = id;
    }

    public OrderItemPK getId() {
        return id;
    }

    public OrderItemEntity setId(OrderItemPK id) {
        this.id = id;
        return this;
    }
}
