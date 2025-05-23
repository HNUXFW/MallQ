package com.example.mallq.service.impl;

import com.example.mallq.entity.User;
import com.example.mallq.repository.UserRepository;
import com.example.mallq.service.UserService;
import com.example.mallq.exception.BusinessException;

import com.example.mallq.utils.PasswordUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.NoSuchAlgorithmException;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public User register(User user) throws NoSuchAlgorithmException {
        // 检查用户名是否已存在
        if (userRepository.findByUsername(user.getUsername()) != null) {
            throw new BusinessException("用户名已存在");
        }
        String salt= PasswordUtil.generateSalt();
        user.setSalt(salt);
        String hashedPassword = PasswordUtil.hashWithSalt(user.getPassword(), salt);
        // 加密密码
        user.setPassword(hashedPassword);
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public User login(String username, String password) throws NoSuchAlgorithmException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new BusinessException("用户不存在");
        }

        if (!PasswordUtil.verify(password, user.getPassword(), user.getSalt())) {
            throw new BusinessException("密码错误");
        }
        
        if (!"ACTIVE".equals(user.getStatus())) {
            throw new BusinessException("账户已被禁用");
        }
        
        return user;
    }


} 