package com.example.mallq.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    // 交换机名称
    public static final String ORDER_EXCHANGE = "order.exchange";
    
    // 队列名称
    public static final String INVENTORY_QUEUE = "inventory.queue";
    public static final String NOTIFICATION_QUEUE = "notification.queue";
    public static final String LOGGING_QUEUE = "logging.queue";
    
    // 路由键
    public static final String INVENTORY_ROUTING_KEY = "order.inventory";
    public static final String NOTIFICATION_ROUTING_KEY = "order.notification";
    public static final String LOGGING_ROUTING_KEY = "order.logging";

    @Bean
    public DirectExchange orderExchange() {
        return new DirectExchange(ORDER_EXCHANGE);
    }

    @Bean
    public Queue inventoryQueue() {
        return new Queue(INVENTORY_QUEUE);
    }

    @Bean
    public Queue notificationQueue() {
        return new Queue(NOTIFICATION_QUEUE);
    }

    @Bean
    public Queue loggingQueue() {
        return new Queue(LOGGING_QUEUE);
    }

    @Bean
    public Binding inventoryBinding() {
        return BindingBuilder.bind(inventoryQueue())
                .to(orderExchange())
                .with(INVENTORY_ROUTING_KEY);
    }

    @Bean
    public Binding notificationBinding() {
        return BindingBuilder.bind(notificationQueue())
                .to(orderExchange())
                .with(NOTIFICATION_ROUTING_KEY);
    }

    @Bean
    public Binding loggingBinding() {
        return BindingBuilder.bind(loggingQueue())
                .to(orderExchange())
                .with(LOGGING_ROUTING_KEY);
    }
} 