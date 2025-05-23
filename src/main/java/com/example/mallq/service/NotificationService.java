package com.example.mallq.service;

import com.example.mallq.dto.OrderMessage;



public interface NotificationService {
    public void handleNotification(OrderMessage message);
} 