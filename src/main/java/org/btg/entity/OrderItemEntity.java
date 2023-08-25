package org.btg.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "order_item")
public class OrderItemEntity {

    @EmbeddedId
    private OrderItemPK id;
    @Column(name = "product_name")
    private String productName;
    @Column(name = "quantity")
    private Integer quantity;
    @Column(name = "unit_price")
    private BigDecimal unitPrice;
    @Column(name = "total_price")
    private BigDecimal totalPrice;

    @Column(name = "create_at", nullable = false, updatable = false)
    @CreationTimestamp
    private Date createAt = new Date();

    @Column(name = "update_at", nullable = false)
    @UpdateTimestamp
    private Date updateAt = new Date();

    public OrderItemEntity() {
    }

    public String getProductName() {
        return productName;
    }

    public OrderItemEntity setProductName(String productName) {
        this.productName = productName;
        return this;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public OrderItemEntity setQuantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public OrderItemEntity setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
        return this;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public OrderItemEntity setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
        return this;
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

    public Date getCreateAt() {
        return createAt;
    }

    public OrderItemEntity setCreateAt(Date createAt) {
        this.createAt = createAt;
        return this;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public OrderItemEntity setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
        return this;
    }
}
