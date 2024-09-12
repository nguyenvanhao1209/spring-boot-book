package com.example.book.service.impl;

import com.example.book.dto.BookDTO;
import com.example.book.search.SearchBookCriteria;
import com.example.book.entity.Book;
import com.example.book.entity.Publisher;
import com.example.book.entity.User;
import com.example.book.exception.ResourceNotFoundException;
import com.example.book.mapper.BookMapper;
import com.example.book.repository.BookRepository;
import com.example.book.repository.PublisherRepository;
import com.example.book.repository.UserRepository;
import com.example.book.request.CreateBookRequest;
import com.example.book.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;
    private final UserRepository userRepository;
    private final BookMapper bookMapper;
    @Override
    public BookDTO createBook(CreateBookRequest createBookRequest) {
        Publisher publisher = publisherRepository.findById(createBookRequest.getPublisherId()).orElseThrow(
                () -> new ResourceNotFoundException("Publisher not found")
        );
        List<User> users = createBookRequest.getUserIds().stream()
                .map(userId -> userRepository.findById(userId)
                        .orElseThrow(() -> new RuntimeException("User not found")))
                .collect(Collectors.toList());
        Book book = new Book();
        book.setTitle(createBookRequest.getTitle());
        book.setPublisher(publisher);
        book.setUsers(users);
        book.setPublishedAt(LocalDate.now());
        book = bookRepository.save(book);
        return bookMapper.toDTO(book);
    }

    @Override
    public BookDTO getBook(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Book not found")
        );
        return bookMapper.toDTO(book);
    }

    @Override
    public List<BookDTO> getAllBooks(SearchBookCriteria searchBookCriteria) {
        List<Book> books = bookRepository.findAll(searchBookCriteria);
        return bookMapper.toDTOs(books);
    }

    @Override
    public BookDTO updateBook(Long id, CreateBookRequest createBookRequest) {
        Publisher publisher = publisherRepository.findById(createBookRequest.getPublisherId()).orElseThrow(
                () -> new ResourceNotFoundException("Publisher not found")
        );
        List<User> users = createBookRequest.getUserIds().stream()
                .map(userId -> userRepository.findById(userId)
                        .orElseThrow(() -> new ResourceNotFoundException("User not found")))
                .collect(Collectors.toList());
        Book book = bookRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Book not found")
        );
        book.setTitle(createBookRequest.getTitle());
        book.setPublisher(publisher);
        book.setUsers(users);
        book = bookRepository.update(book);
        return bookMapper.toDTO(book);
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.delete(id);
    }
}
