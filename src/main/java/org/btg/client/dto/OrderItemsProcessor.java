package org.btg.client.dto;

import io.quarkus.runtime.annotations.RegisterForReflection;

import java.math.BigDecimal;

@RegisterForReflection
public class OrderItemsProcessor {

    public String productName;
    public Integer quantity;
    public BigDecimal price;

    public OrderItemsProcessor(String productName, Integer quantity, BigDecimal price) {
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
    }

    public OrderItemsProcessor() {
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
