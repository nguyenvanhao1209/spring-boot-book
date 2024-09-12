package com.example.book.mapper;

import com.example.book.dto.PublisherDTO;
import com.example.book.entity.Publisher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublisherMapper {
    public Publisher toEntity(PublisherDTO publisherDTO) {
        Publisher publisher = new Publisher();
        publisher.setId(publisherDTO.getId());
        publisher.setName(publisherDTO.getName());
        return publisher;
    }

    public PublisherDTO toDTO(Publisher publisher) {
        PublisherDTO publisherDTO = new PublisherDTO();
        publisherDTO.setId(publisher.getId());
        publisherDTO.setName(publisher.getName());
        return publisherDTO;
    }

    public List<Publisher> toEntities(List<PublisherDTO> publisherDTOList) {
        return publisherDTOList.stream().map(this::toEntity).toList();
    }

    public List<PublisherDTO> toDTOs(List<Publisher> publisherList) {
        return publisherList.stream().map(this::toDTO).toList();
    }
}
