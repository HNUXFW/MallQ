package com.example.mallq.controller;

import com.example.mallq.entity.User;
import com.example.mallq.service.UserService;
import com.example.mallq.vo.LoginRequest;
import com.example.mallq.vo.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;

@Tag(name="用户管理",description = "用户相关的API")
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    @Operation(summary = "注册用户", description = "创建新用户")
    public Result<User> register(@Valid @RequestBody User user) throws NoSuchAlgorithmException {
        User registeredUser = userService.register(user);
        registeredUser.setPassword(null); // 不返回密码
        return Result.success(registeredUser);
    }

    @PostMapping("/login")
    @Operation(summary = "登录用户", description = "用户登录")
    public Result<User> login(@Valid @RequestBody LoginRequest loginRequest) throws NoSuchAlgorithmException {
        User user = userService.login(loginRequest.getUsername(), loginRequest.getPassword());
        user.setPassword(null); // 不返回密码
        return Result.success(user);
    }
}
