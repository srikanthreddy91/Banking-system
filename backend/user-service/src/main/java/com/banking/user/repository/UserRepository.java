package com.banking.user.repository;

import com.banking.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Find a user by their email address
     */
    Optional<User> findByEmail(String email);

    /**
     * Check if a user exists with the given email
     */
    boolean existsByEmail(String email);
}