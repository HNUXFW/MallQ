package com.example.mallq.service;

import com.example.mallq.entity.Order;

public interface OrderService {
    Order createOrder(Long userId, Long productId, Integer quantity);
    Order getOrder(Long id);
}

