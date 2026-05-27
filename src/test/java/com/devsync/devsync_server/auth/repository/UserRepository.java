package com.devsync.devsync_server.auth.repository;

import com.devsync.devsync_server.auth.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository
        extends JpaRepository<User, Long> {

    Optional<User> findByEmail(
            String email
    );

    Optional<User> findByUsername(
            String username
    );

    boolean existsByEmail(
            String email
    );

    boolean existsByUsername(
            String username
    );
}