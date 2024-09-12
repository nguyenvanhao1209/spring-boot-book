package com.example.book.repository;

import com.example.book.search.SearchBookCriteria;
import com.example.book.entity.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {
    Book save(Book book);
    Optional<Book> findById(Long id);
    List<Book> findAll(SearchBookCriteria searchBookCriteria);
    Book update(Book book);
    void delete(Long id);
}
