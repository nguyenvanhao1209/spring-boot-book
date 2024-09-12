package com.example.book.response;

import com.example.book.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiResponse {
    private String message;
    private Object data;
}
