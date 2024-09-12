package com.example.book.controller;

import com.example.book.dto.LoginDTO;
import com.example.book.dto.UserDTO;
import com.example.book.request.LoginRequest;
import com.example.book.request.RegisterRequest;
import com.example.book.response.ApiResponse;
import com.example.book.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @PostMapping("/register/")
    public ResponseEntity<ApiResponse> register(
            @Valid @RequestBody RegisterRequest registerRequest
    ) throws IllegalAccessException {
        UserDTO userDTO = userService.registerUser(registerRequest);
        return ResponseEntity.ok(new ApiResponse("User registered successfully", userDTO));
    }

    @PostMapping("/login/")
    public ResponseEntity<ApiResponse> login(
            @Valid @RequestBody LoginRequest loginRequest
    ) {
        LoginDTO loginDTO = userService.loginUser(loginRequest);
        return ResponseEntity.ok(new ApiResponse("User logged in successfully", loginDTO));
    }
}
