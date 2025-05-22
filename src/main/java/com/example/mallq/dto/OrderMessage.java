package com.example.mallq.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.math.BigDecimal;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id", scope = OrderMessage.class)
public class OrderMessage implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private Long orderId;
    private String orderNo;
    private Long productId;
    private Integer quantity;
    private BigDecimal amount;
    private String type; // INVENTORY_CHECK, NOTIFICATION, LOGGING
} 