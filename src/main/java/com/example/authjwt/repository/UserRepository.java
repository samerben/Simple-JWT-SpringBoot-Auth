package com.example.authjwt.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.authjwt.models.UserModel;

public interface UserRepository extends JpaRepository<UserModel, Long> {
    Optional<UserModel> findByEmail(String email);
}