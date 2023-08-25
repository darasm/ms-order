package org.btg.dto;

import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.math.BigDecimal;

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
}
