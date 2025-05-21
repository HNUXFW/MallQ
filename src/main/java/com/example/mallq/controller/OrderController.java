package com.example.mallq.controller;

import com.example.mallq.entity.Order;
import com.example.mallq.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestParam Long userId,
                                           @RequestParam Long productId,
                                           @RequestParam Integer quantity) {
        Order order = orderService.createOrder(userId, productId, quantity);
        return ResponseEntity.ok(order);
    }
} 