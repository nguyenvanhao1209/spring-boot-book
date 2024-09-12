package com.example.book.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginDTO {
    private String accessToken;
    private String refreshToken;
}
