package com.devsync.devsync_server.auth.controller;

import com.devsync.devsync_server.auth.dto.LoginRequest;
import com.devsync.devsync_server.auth.dto.RegisterRequest;
import com.devsync.devsync_server.auth.dto.UserResponse;
import com.devsync.devsync_server.auth.service.AuthService;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(

            @Valid
            @RequestBody
            RegisterRequest request

    ) {

        UserResponse response =
                authService.register(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponse> login(

            @Valid
            @RequestBody
            LoginRequest request

    ) {

        UserResponse response =
                authService.login(request);

        return ResponseEntity.ok(response);
    }
}