package com.example.mallq.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data//Lombok自动生成getter和setter方法
@Entity//JPA对象映射为数据库表
@Table(name = "orders")//JPA注解，指示对应的数据库表名称
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String orderNo;
    private Long userId;
    private Long productId;
    private Integer quantity;
    private BigDecimal amount;
    private String status;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
} 