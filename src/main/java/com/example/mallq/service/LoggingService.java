package com.example.mallq.service;

public interface LoggingService {
    void logInfo(String message);
    void logError(String message, Throwable throwable);
}
