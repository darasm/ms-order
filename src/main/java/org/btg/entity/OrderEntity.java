package org.btg.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "order_table", indexes = @Index(name = "idx_order_client_id", columnList = "client_id"))
public class OrderEntity {
    @Id
    private Long id;
    @Column(name = "client_id", nullable = false)
    private Long clientId;
    @Column(name = "total_price")
    private BigDecimal totalPrice;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id", referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "fk_order_request_order_request_item"))
    private List<OrderItemEntity> items;

    @Column(name = "create_at", nullable = false, updatable = false)
    @CreationTimestamp
    private Date createAt = new Date();
    @Column(name = "update_at", nullable = false)
    @UpdateTimestamp
    private Date updateAt = new Date();

    public OrderEntity() {
        //Default constructor
    }

    public List<OrderItemEntity> getItems() {
        return items;
    }

    public OrderEntity setItems(List<OrderItemEntity> items) {
        this.items = items;
        return this;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderEntity that = (OrderEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(clientId, that.clientId) && Objects.equals(totalPrice, that.totalPrice) && Objects.equals(items, that.items) && Objects.equals(createAt, that.createAt) && Objects.equals(updateAt, that.updateAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, clientId, totalPrice, items, createAt, updateAt);
    }

    @Override
    public String toString() {
        return "OrderEntity{" +
                "id=" + id +
                ", clientId=" + clientId +
                ", totalPrice=" + totalPrice +
                ", items=" + items +
                ", createAt=" + createAt +
                ", updateAt=" + updateAt +
                '}';
    }
}
