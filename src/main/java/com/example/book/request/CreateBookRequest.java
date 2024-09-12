package com.example.book.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Data
public class CreateBookRequest {
    @NotBlank(message = "Title is required")
    private String title;
    private Long publisherId;
    private List<Long> userIds;
}
