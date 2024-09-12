package com.example.book.service;

import com.example.book.dto.BookDTO;
import com.example.book.search.SearchBookCriteria;
import com.example.book.request.CreateBookRequest;

import java.util.List;

public interface BookService {
    public BookDTO createBook(CreateBookRequest createBookRequest);
    public BookDTO getBook(Long id);
    public List<BookDTO> getAllBooks(SearchBookCriteria searchBookCriteria);
    public BookDTO updateBook(Long id, CreateBookRequest createBookRequest);
    public void deleteBook(Long id);
}
