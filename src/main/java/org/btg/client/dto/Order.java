package org.btg.client.dto;

import java.math.BigDecimal;
import java.util.List;

public class Order {
    private Integer orderId;
    private BigDecimal totalPrice;
    private List<OrderItem> items;
}
