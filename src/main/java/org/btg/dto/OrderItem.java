package org.btg.dto;

import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.math.BigDecimal;
import java.util.Objects;

public class OrderItem {

    @Schema(title = "Product Name", example = "French Fries", type = SchemaType.STRING)
    private String productName;
    @Schema(title = "Amount of product", example = "10", type = SchemaType.INTEGER)
    private Integer quantity;
    @Schema(title = "Unit Product Price", example = "1.00", type = SchemaType.NUMBER)
    private BigDecimal unitPrice;
    @Schema(title = "Total Product Price", example = "10.00", type = SchemaType.NUMBER)
    private BigDecimal totalPrice;

    public OrderItem() {
        // Default constructor
    }

    public String getProductName() {
        return productName;
    }

    public OrderItem setProductName(String productName) {
        this.productName = productName;
        return this;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public OrderItem setQuantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public OrderItem setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
        return this;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public OrderItem setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItem orderItem = (OrderItem) o;
        return Objects.equals(productName, orderItem.productName) && Objects.equals(quantity, orderItem.quantity) && Objects.equals(unitPrice, orderItem.unitPrice) && Objects.equals(totalPrice, orderItem.totalPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productName, quantity, unitPrice, totalPrice);
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "productName='" + productName + '\'' +
                ", quantity=" + quantity +
                ", unitPrice=" + unitPrice +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
