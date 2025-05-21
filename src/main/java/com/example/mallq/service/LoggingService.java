package com.example.mallq.service;

import com.example.mallq.dto.OrderMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LoggingService {

    @RabbitListener(queues = "#{loggingQueue.name}")
    public void handleLogging(OrderMessage message) {
        log.info("收到日志消息: {}", message);
        // 这里可以实现将日志保存到数据库或文件系统
        try {
            // 模拟日志记录
            log.info("记录订单日志，订单号: {}, 类型: {}, 金额: {}", 
                    message.getOrderNo(), 
                    message.getType(), 
                    message.getAmount());
        } catch (Exception e) {
            log.error("日志记录失败，订单号: {}, 错误: {}", message.getOrderNo(), e.getMessage());
        }
    }
} 