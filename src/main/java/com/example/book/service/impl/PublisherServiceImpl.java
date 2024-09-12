package com.example.book.service.impl;

import com.example.book.dto.PublisherDTO;
import com.example.book.entity.Publisher;
import com.example.book.exception.ResourceNotFoundException;
import com.example.book.mapper.PublisherMapper;
import com.example.book.repository.PublisherRepository;
import com.example.book.service.PublisherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PublisherServiceImpl implements PublisherService {
    private final PublisherRepository publisherRepository;
    private final PublisherMapper publisherMapper;

    @Override
    public PublisherDTO createPublisher(PublisherDTO publisherDTO) {
        Publisher publisher = publisherMapper.toEntity(publisherDTO);
        Publisher savedPublisher = publisherRepository.save(publisher);
        return publisherMapper.toDTO(savedPublisher);
    }

    @Override
    public PublisherDTO getPublisher(Long id) {
        Publisher publisher = publisherRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Publisher not found")
        );
        return publisherMapper.toDTO(publisher);
    }

    @Override
    public List<PublisherDTO> getAllPublishers() {
        List<Publisher> publishers = publisherRepository.findAll();
        return publisherMapper.toDTOs(publishers);
    }
}
