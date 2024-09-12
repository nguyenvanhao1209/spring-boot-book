package com.example.book.controller;

import com.example.book.dto.PublisherDTO;
import com.example.book.response.ApiResponse;
import com.example.book.service.PublisherService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/publishers")
@RequiredArgsConstructor
public class PublisherController {
    private final PublisherService publisherService;

    @PostMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse> createPublisher(
            @Valid @RequestBody PublisherDTO publisherDTO
    ) {
        return ResponseEntity.ok(new ApiResponse("Publisher created successfully", publisherService.createPublisher(publisherDTO)));
    }

    @GetMapping("/")
    public ResponseEntity<ApiResponse> getAllPublisher() {
        return ResponseEntity.ok(new ApiResponse("List all Publisher", publisherService.getAllPublishers()));
    }

    @GetMapping("/{id}/")
    public ResponseEntity<ApiResponse> getPublisher(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(new ApiResponse("Publisher fetched successfully", publisherService.getPublisher(id)));
    }
}
