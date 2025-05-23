package com.example.mallq.controller;

import com.example.mallq.entity.Order;
import com.example.mallq.service.OrderService;
import com.example.mallq.vo.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "订单管理", description = "订单相关的API")
@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @Operation(summary = "创建订单", description = "创建新订单并发送相关消息到消息队列")
    @PostMapping
    public Result<Order> createOrder(
            @Parameter(description = "用户ID") @RequestParam Long userId,
            @Parameter(description = "商品ID") @RequestParam Long productId,
            @Parameter(description = "购买数量") @RequestParam Integer quantity) {
        Order order = orderService.createOrder(userId, productId, quantity);
        return Result.success(order);
    }

    @Operation(summary = "获取订单详情", description = "根据订单ID获取订单详细信息")
    @GetMapping("/{id}")
    public Result<Order> getOrder(
            @Parameter(description = "订单ID") @PathVariable Long id
    ){
        Order order=orderService.getOrder(id);
        return Result.success(order);
    }
} 