package com.example.book.service;

import com.example.book.dto.PublisherDTO;

import java.util.List;

public interface PublisherService {
    PublisherDTO createPublisher(PublisherDTO publisherDTO);
    PublisherDTO getPublisher(Long id);
    List<PublisherDTO> getAllPublishers();
}
