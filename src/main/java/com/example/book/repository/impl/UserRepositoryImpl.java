package com.example.book.repository.impl;

import com.example.book.entity.QUser;
import com.example.book.entity.User;
import com.example.book.repository.UserRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Transactional
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {
    @PersistenceContext
    private final EntityManager entityManager;

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.ofNullable(jpaQueryFactory.selectFrom(QUser.user)
                .where(QUser.user.email.eq(email))
                .fetchOne());
    }

    @Override
    public User save(User user) {
        entityManager.persist(user);
        return user;
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.ofNullable(jpaQueryFactory.selectFrom(QUser.user)
                .where(QUser.user.id.eq(id))
                .fetchOne());
    }
}
