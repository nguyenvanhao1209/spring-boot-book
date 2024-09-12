package com.example.book.controller;

import com.example.book.dto.UserDTO;
import com.example.book.response.ApiResponse;
import com.example.book.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/me/")
    public ResponseEntity<ApiResponse> me() {
        UserDTO userDTO = userService.getCurrentUser();
        return ResponseEntity.ok(new ApiResponse("User retrieved successfully", userDTO));
    }
}
