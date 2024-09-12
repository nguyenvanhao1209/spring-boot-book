package com.example.book.service;

import com.example.book.dto.LoginDTO;
import com.example.book.dto.UserDTO;
import com.example.book.entity.User;
import com.example.book.request.LoginRequest;
import com.example.book.request.RegisterRequest;

public interface UserService {
    public UserDTO registerUser(RegisterRequest registerRequest) throws IllegalAccessException;
    public LoginDTO loginUser(LoginRequest loginRequest);
    public User getUserByEmail(String email);
    public UserDTO getUserById(Long id);
    public UserDTO getCurrentUser();
}
