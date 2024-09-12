package com.example.book.repository;

import com.example.book.entity.Publisher;

import java.util.List;
import java.util.Optional;

public interface PublisherRepository {
    Publisher save(Publisher publisher);
    Optional<Publisher> findById(Long id);
    List<Publisher> findAll();
}
