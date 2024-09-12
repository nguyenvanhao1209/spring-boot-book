package com.example.book.controller;

import com.example.book.search.SearchBookCriteria;
import com.example.book.request.CreateBookRequest;
import com.example.book.response.ApiResponse;
import com.example.book.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @PostMapping("/")
    public ResponseEntity<ApiResponse> createBook(
           @Valid @RequestBody CreateBookRequest createBookRequest
    ) {
        return ResponseEntity.ok(new ApiResponse("Book created successfully", bookService.createBook(createBookRequest)));
    }

    @GetMapping("/")
    public ResponseEntity<ApiResponse> getAllBooks(
            SearchBookCriteria searchBookCriteria
    ) {
        return ResponseEntity.ok(new ApiResponse("Books fetched successfully", bookService.getAllBooks(searchBookCriteria)));
    }

    @GetMapping("/{id}/")
    public ResponseEntity<ApiResponse> getBook(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(new ApiResponse("Book fetched successfully", bookService.getBook(id)));
    }

    @PutMapping("/{id}/")
    @PostAuthorize("returnObject.body.data.users.contains(authentication.principal)")
    public ResponseEntity<ApiResponse> updateBook(
            @PathVariable Long id,
            @Valid @RequestBody CreateBookRequest createBookRequest
    ) {
        return ResponseEntity.ok(new ApiResponse("Book updated successfully", bookService.updateBook(id, createBookRequest)));
    }

    @DeleteMapping("/{id}/")
    public ResponseEntity<ApiResponse> deleteBook(
            @PathVariable Long id
    ) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
}
