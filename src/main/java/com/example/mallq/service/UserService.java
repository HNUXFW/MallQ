package com.example.mallq.service;
import com.example.mallq.entity.User;

import java.security.NoSuchAlgorithmException;

public interface UserService {
    User register(User user) throws NoSuchAlgorithmException;
    User login(String username, String password) throws NoSuchAlgorithmException;
} 