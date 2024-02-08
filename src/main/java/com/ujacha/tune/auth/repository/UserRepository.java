package com.ujacha.tune.auth.repository;

import com.ujacha.tune.auth.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Boolean existsByUserId(String userId);
    UserEntity findByUserId(String userId);

    Optional<UserEntity> findByEmail(String Email);
}
