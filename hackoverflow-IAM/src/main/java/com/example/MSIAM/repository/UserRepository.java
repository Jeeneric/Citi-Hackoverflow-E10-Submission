package com.example.MSIAM.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.MSIAM.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // define a derived query to find user by username
    Optional<User> findByUsername(String username);
}