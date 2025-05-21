package com.example.mallq.service;

import com.example.mallq.dto.OrderMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class NotificationService {

    @RabbitListener(queues = "#{notificationQueue.name}")
    public void handleNotification(OrderMessage message) {
        log.info("收到通知消息: {}", message);
        // 这里可以实现发送邮件、短信等通知逻辑
        try {
            // 模拟发送通知
            log.info("发送通知给用户，订单号: {}", message.getOrderNo());
            Thread.sleep(100); // 模拟通知发送耗时
            log.info("通知发送成功，订单号: {}", message.getOrderNo());
        } catch (Exception e) {
            log.error("通知发送失败，订单号: {}, 错误: {}", message.getOrderNo(), e.getMessage());
        }
    }
} 