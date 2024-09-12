package com.example.book.dto;

import lombok.Data;
import org.springframework.security.core.parameters.P;

import java.time.LocalDate;
import java.util.List;

@Data
public class BookDTO {
    private Long id;
    private String title;
    private PublisherDTO publisher;
    private LocalDate publishedAt;
    private List<UserDTO> users;
}
