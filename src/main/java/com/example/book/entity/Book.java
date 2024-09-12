package com.example.book.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "publisher_id", nullable = false, foreignKey = @ForeignKey(name = "fk_publisher_book", foreignKeyDefinition = "FOREIGN KEY (publisher_id) REFERENCES publisher(id) ON DELETE CASCADE ON UPDATE CASCADE"))
    private Publisher publisher;
    private LocalDate publishedAt;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "book_user",
            joinColumns = @JoinColumn(name = "book_id", foreignKey = @ForeignKey(name = "fk_book_user_book", foreignKeyDefinition = "FOREIGN KEY (book_id) REFERENCES book(id) ON DELETE CASCADE ON UPDATE CASCADE")),
            inverseJoinColumns = @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_book_user_user", foreignKeyDefinition = "FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE ON UPDATE CASCADE"))
    )
    private List<User> users;
}
