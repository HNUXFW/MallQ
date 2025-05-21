package com.example.mallq.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class OrderMessage {
    private Long orderId;
    private String orderNo;
    private Long productId;
    private Integer quantity;
    private BigDecimal amount;
    private String type; // INVENTORY_CHECK, NOTIFICATION, LOGGING
} 