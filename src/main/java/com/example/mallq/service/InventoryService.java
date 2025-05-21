package com.example.mallq.service;

import com.example.mallq.dto.OrderMessage;
import com.example.mallq.entity.Order;
import com.example.mallq.repository.OrderRepository;
import com.example.mallq.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class InventoryService {
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    @Transactional
    @RabbitListener(queues = "#{inventoryQueue.name}")
    public void handleInventoryCheck(OrderMessage message) {
        log.info("收到库存检查消息: {}", message);
        
        try {
            // 扣减库存
            int updated = productRepository.decreaseStock(message.getProductId(), message.getQuantity());
            
            // 更新订单状态
            if (updated > 0) {
                Order order = orderRepository.findByOrderNo(message.getOrderNo());
                order.setStatus("STOCK_CHECKED");
                order.setUpdateTime(java.time.LocalDateTime.now());
                orderRepository.save(order);
                log.info("库存扣减成功，订单号: {}", message.getOrderNo());
            } else {
                Order order = orderRepository.findByOrderNo(message.getOrderNo());
                order.setStatus("STOCK_FAILED");
                order.setUpdateTime(java.time.LocalDateTime.now());
                orderRepository.save(order);
                log.error("库存不足，订单号: {}", message.getOrderNo());
            }
        } catch (Exception e) {
            log.error("库存扣减失败，订单号: {}, 错误: {}", message.getOrderNo(), e.getMessage());
        }
    }
} 