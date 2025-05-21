package com.example.mallq.repository;

import com.example.mallq.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Order findByOrderNo(String orderNo);//Spring 会自动解析方法名为查询语句，用以从数据库中查找到对应的订单
} 