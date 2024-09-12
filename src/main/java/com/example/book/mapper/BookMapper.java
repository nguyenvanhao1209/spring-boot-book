package com.example.book.mapper;

import com.example.book.dto.BookDTO;
import com.example.book.entity.Book;
import com.example.book.request.CreateBookRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookMapper {
    private final PublisherMapper publisherMapper;
    private final UserMapper userMapper;
    public Book toEntity(BookDTO bookDTO) {
        Book book = new Book();
        book.setId(bookDTO.getId());
        book.setTitle(bookDTO.getTitle());
        book.setPublisher(publisherMapper.toEntity(bookDTO.getPublisher()));
        book.setPublishedAt(bookDTO.getPublishedAt());
        book.setUsers(userMapper.toEntities(bookDTO.getUsers()));
        return book;
    }

    public BookDTO toDTO(Book book) {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(book.getId());
        bookDTO.setTitle(book.getTitle());
        bookDTO.setPublisher(publisherMapper.toDTO(book.getPublisher()));
        bookDTO.setPublishedAt(book.getPublishedAt());
        bookDTO.setUsers(userMapper.toDTOs(book.getUsers()));
        return bookDTO;
    }

    public List<Book> toEntities(List<BookDTO> bookDTOList) {
        return bookDTOList.stream().map(this::toEntity).toList();
    }

    public List<BookDTO> toDTOs(List<Book> bookList) {
        return bookList.stream().map(this::toDTO).toList();
    }

}
