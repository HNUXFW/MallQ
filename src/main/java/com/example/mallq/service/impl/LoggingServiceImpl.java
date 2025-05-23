package com.example.mallq.service.impl;

import com.example.mallq.service.LoggingService;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LoggingServiceImpl implements LoggingService {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public void logInfo(String message) {
        String formattedMessage = formatLog("INFO", message);
        System.out.println(formattedMessage); // 假设日志输出到控制台
    }

    @Override
    public void logError(String message, Throwable throwable) {
        String formattedMessage = formatLog("ERROR", message);
        System.err.println(formattedMessage); // 错误日志输出到标准错误流
        if (throwable != null) {
            throwable.printStackTrace();
        }
    }

    private String formatLog(String level, String message) {
        String timestamp = LocalDateTime.now().format(formatter);
        return String.format("[%s] [%s] %s", timestamp, level, message);
    }
}