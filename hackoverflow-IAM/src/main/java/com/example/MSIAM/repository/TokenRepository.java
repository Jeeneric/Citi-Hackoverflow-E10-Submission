package com.example.MSIAM.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.MSIAM.entity.Token;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {
    Optional<Token> findByAuthorization(UUID authorization);
}