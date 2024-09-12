package com.example.book.repository;

import com.example.book.entity.User;

import java.util.Optional;

public interface UserRepository {
    Optional<User> findByEmail(String email);
    User save(User user);
    Optional<User> findById(Long id);
}
