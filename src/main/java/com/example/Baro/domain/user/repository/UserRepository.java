package com.example.Baro.domain.user.repository;

import com.example.Baro.domain.user.entity.User;
import com.example.Baro.domain.user.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByUserRole(UserRole userRole);
}
