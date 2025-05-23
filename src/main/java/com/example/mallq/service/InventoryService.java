package com.example.mallq.service;

import com.example.mallq.dto.OrderMessage;

public interface InventoryService {

    public void handleInventoryCheck(OrderMessage message);
} 