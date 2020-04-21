package com.backend.toyswap.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.backend.toyswap.model.User;

/**
 * Spring repository interface for Users.
 * Allow us to access the methods in JPA Repository
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
