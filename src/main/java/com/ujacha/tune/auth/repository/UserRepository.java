package com.ujacha.tune.auth.repository;

import com.ujacha.tune.auth.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, String> {
}
