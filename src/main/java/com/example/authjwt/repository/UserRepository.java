package com.example.authjwt.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.authjwt.models.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);
}