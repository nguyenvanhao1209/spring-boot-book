package com.example.book.repository.impl;

import com.example.book.entity.Publisher;
import com.example.book.entity.QPublisher;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import com.example.book.repository.PublisherRepository;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
@RequiredArgsConstructor
public class PublisherRepositoryImpl implements PublisherRepository {

    @PersistenceContext
    private final EntityManager entityManager;

    private final JPAQueryFactory jpaQueryFactory;
    @Override
    public Publisher save(Publisher publisher) {
        entityManager.persist(publisher);
        return publisher;
    }

    @Override
    public Optional<Publisher> findById(Long id) {
        return Optional.ofNullable(jpaQueryFactory.selectFrom(QPublisher.publisher)
                .where(QPublisher.publisher.id.eq(id))
                .fetchOne());
    }

    @Override
    public List<Publisher> findAll() {
        return jpaQueryFactory.selectFrom(QPublisher.publisher)
                .fetch();
    }
}
