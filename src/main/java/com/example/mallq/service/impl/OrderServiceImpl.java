package com.example.mallq.service.impl;

import com.example.mallq.config.RabbitMQConfig;
import com.example.mallq.dto.OrderMessage;
import com.example.mallq.entity.Order;
import com.example.mallq.entity.Product;
import com.example.mallq.repository.OrderRepository;
import com.example.mallq.repository.ProductRepository;
import com.example.mallq.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
        //实现依赖注入
        private final OrderRepository orderRepository;
        private final ProductRepository productRepository;
        private final RabbitTemplate rabbitTemplate;
        //OrderRepository 是 Spring Data JPA 自动生成并注册的 Bean。
        //RabbitTemplate 是 Spring Boot 自动配置的 Bean。
        //@RequiredArgsConstructor 让 Spring 可以通过构造函数注入这些 Bean。

        @Transactional
        public Order createOrder(Long userId, Long productId, Integer quantity) {
            // 1. 查询商品
            Product product = productRepository.findById(productId)
                    .orElseThrow(() -> new RuntimeException("商品不存在"));

            // 2. 创建订单
            Order order = new Order();
            order.setOrderNo(UUID.randomUUID().toString());
            order.setUserId(userId);
            order.setProductId(productId);
            order.setQuantity(quantity);
            order.setAmount(product.getPrice().multiply(java.math.BigDecimal.valueOf(quantity)));
            order.setStatus("CREATED");
            order.setCreateTime(LocalDateTime.now());
            order.setUpdateTime(LocalDateTime.now());

            order = orderRepository.save(order);//这里的save方法，会自动更新order的id，所以下面的order.getId()是正确的

            // 3. 发送消息到各个队列
            OrderMessage message = new OrderMessage();
            message.setOrderId(order.getId());
            message.setOrderNo(order.getOrderNo());
            message.setProductId(productId);
            message.setQuantity(quantity);
            message.setAmount(order.getAmount());

            // 发送库存检查消息
            message.setType("INVENTORY_CHECK");//看来存放在队列对象必须含有type属性
            rabbitTemplate.convertAndSend(RabbitMQConfig.ORDER_EXCHANGE,
                    RabbitMQConfig.INVENTORY_ROUTING_KEY,
                    message);

            // 发送通知消息
            message.setType("NOTIFICATION");
            rabbitTemplate.convertAndSend(RabbitMQConfig.ORDER_EXCHANGE,
                    RabbitMQConfig.NOTIFICATION_ROUTING_KEY,
                    message);

            // 发送日志消息
            message.setType("LOGGING");
            rabbitTemplate.convertAndSend(RabbitMQConfig.ORDER_EXCHANGE,
                    RabbitMQConfig.LOGGING_ROUTING_KEY,
                    message);

            return order;
        }
        @Transactional
        public Order getOrder(Long id) {
            Order order = orderRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("订单不存在"));
            return order;
        }
    }
