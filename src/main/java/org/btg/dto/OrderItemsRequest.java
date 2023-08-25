package org.btg.dto;

import io.quarkus.runtime.annotations.RegisterForReflection;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.math.BigDecimal;

@RegisterForReflection
public class OrderItemsRequest {
    @Schema(title = "Product Name", example = "French Fries", type = SchemaType.STRING, required = true)
    public String productName;
    @Schema(title = "Amount of product", example = "10", type = SchemaType.INTEGER, required = true)
    public Integer quantity;
    @Schema(title = "Unit Product Price", example = "1.00", type = SchemaType.NUMBER, required = true)
    public BigDecimal price;

    public OrderItemsRequest(String productName, Integer quantity, BigDecimal price) {
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
    }

    public OrderItemsRequest() {
    }

    public String getProductName() {
        return productName;
    }

    public OrderItemsRequest setProductName(String productName) {
        this.productName = productName;
        return this;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public OrderItemsRequest setQuantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public OrderItemsRequest setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    @Override
    public String toString() {
        return "OrderItemsProcessor{" +
                "productName='" + productName + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}
