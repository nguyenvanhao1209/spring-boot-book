package com.example.book.service.impl;

import com.example.book.dto.LoginDTO;
import com.example.book.dto.UserDTO;
import com.example.book.entity.User;
import com.example.book.mapper.UserMapper;
import com.example.book.repository.UserRepository;
import com.example.book.request.LoginRequest;
import com.example.book.request.RegisterRequest;
import com.example.book.service.JwtService;
import com.example.book.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    @Override
    public UserDTO registerUser(RegisterRequest registerRequest) throws IllegalAccessException {
        if (userRepository.findByEmail(registerRequest.getEmail()).isPresent()) {
            throw new IllegalAccessException("Email already exists");
        }
        String encodedPassword = passwordEncoder.encode(registerRequest.getPassword());
        User user = new User();
        user.setEmail(registerRequest.getEmail());
        user.setPassword(encodedPassword);
        user.setRole(User.Role.USER);

        User savedUser = userRepository.save(user);
        return userMapper.toDTO(savedUser);
    }

    @Override
    public LoginDTO loginUser(LoginRequest loginRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );
        UserDetails userDetails = getUserByEmail(loginRequest.getEmail());
        String token = jwtService.generateToken(userDetails);
        String refreshToken = jwtService.refreshToken(userDetails);
        return new LoginDTO(token, refreshToken);
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    @Override
    public UserDTO getUserById(Long id) {
        return null;
    }

    @Override
    public UserDTO getCurrentUser() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByEmail(userDetails.getUsername()).orElseThrow(
                () -> new IllegalArgumentException("User not found")
        );
        return userMapper.toDTO(user);
    }
}
