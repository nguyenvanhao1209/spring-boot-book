package com.example.book.dto;

import com.example.book.entity.User;
import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String email;
    private User.Role role;
}
