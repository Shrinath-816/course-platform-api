package com.courseplatform.api.controller;

import com.courseplatform.api.dto.LoginRequest;
import com.courseplatform.api.dto.LoginResponse;
import com.courseplatform.api.dto.RegisterRequest;
import com.courseplatform.api.entity.User;
import com.courseplatform.api.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public User register(@RequestBody RegisterRequest req) {
        return authService.register(req);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest req) {
        return authService.login(req);
    }
}

