package org.btg.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "order", indexes = @Index(name = "idx_order_client_id", columnList = "client_id"))
public class OrderEntity {
    @Id
    @Column(nullable = false)
    private Long id;
    @Column(name = "client_id", nullable = false)
    private Long clientId;
    @Column(name = "total_price")
    private BigDecimal totalPrice;
    @Column(name = "create_at", nullable = false, updatable = false)
    @CreationTimestamp
    private Date createAt = new Date();
    @Column(name = "update_at", nullable = false)
    @UpdateTimestamp
    private Date updateAt = new Date();

    public OrderEntity() {
        //Default constructor
    }

    public Long getClientId() {
        return clientId;
    }

    public OrderEntity setClientId(Long clientId) {
        this.clientId = clientId;
        return this;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public OrderEntity setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
        return this;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public OrderEntity setCreateAt(Date createAt) {
        this.createAt = createAt;
        return this;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public OrderEntity setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
        return this;
    }

    public Long getId() {
        return id;
    }

    public OrderEntity setId(Long id) {
        this.id = id;
        return this;
    }
}
