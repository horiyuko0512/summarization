package com.example.summarization.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.summarization.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
