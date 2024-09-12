package com.example.book.repository.impl;

import com.example.book.search.SearchBookCriteria;
import com.example.book.entity.Book;
import com.example.book.entity.Publisher;
import com.example.book.entity.QBook;
import com.example.book.entity.User;
import com.example.book.repository.BookRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
@RequiredArgsConstructor
public class BookRepositoryImpl implements BookRepository {
    private final EntityManager entityManager;
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Book save(Book book) {
        entityManager.persist(book);
        return book;
    }
    @Override
    public Optional<Book> findById(Long id) {
        return Optional.ofNullable(jpaQueryFactory.selectFrom(QBook.book)
                .where(QBook.book.id.eq(id))
                .fetchOne());
    }

    @Override
    public List<Book> findAll(SearchBookCriteria searchBookCriteria) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Book> criteriaQuery = criteriaBuilder.createQuery(Book.class);
        Root<Book> book = criteriaQuery.from(Book.class);
        List<Predicate> predicates = new ArrayList<>();

        if (searchBookCriteria.getTitle() != null) {
            predicates.add(criteriaBuilder.like(book.get("title"), "%" + searchBookCriteria.getTitle() + "%"));
        }

        if (searchBookCriteria.getAuthor() != null) {
            Join<Book, User> userJoin = book.join("users");
            predicates.add(criteriaBuilder.like(userJoin.get("email"), "%" + searchBookCriteria.getAuthor() + "%"));
        }

        if (searchBookCriteria.getPublisher() != null) {
            Join<Book, Publisher> publisherJoin = book.join("publisher");
            predicates.add(criteriaBuilder.like(publisherJoin.get("name"), "%" + searchBookCriteria.getPublisher() + "%"));
        }

        if (searchBookCriteria.getStartDate() != null && searchBookCriteria.getEndDate() != null) {
            predicates.add(criteriaBuilder.between(book.get("publishedAt"), searchBookCriteria.getStartDate(), searchBookCriteria.getEndDate()));
        }

        criteriaQuery.where(predicates.toArray(new Predicate[0]));

        if (searchBookCriteria.getSortBy() != null && searchBookCriteria.getDirection() != null) {
            if (searchBookCriteria.getDirection().equals("asc")) {
                criteriaQuery.orderBy(criteriaBuilder.asc(book.get(searchBookCriteria.getSortBy())));
            } else {
                criteriaQuery.orderBy(criteriaBuilder.desc(book.get(searchBookCriteria.getSortBy())));
            }
        }

        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public Book update(Book book) {
        return jpaQueryFactory.update(QBook.book)
                .set(QBook.book.title, book.getTitle())
                .set(QBook.book.publisher, book.getPublisher())
                .set(QBook.book.publishedAt, book.getPublishedAt())
                .where(QBook.book.id.eq(book.getId()))
                .execute() == 1 ? book : null;
    }

    @Override
    public void delete(Long id) {
        jpaQueryFactory.delete(QBook.book)
                .where(QBook.book.id.eq(id))
                .execute();
    }

}
