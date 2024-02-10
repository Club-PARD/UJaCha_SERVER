package com.ujacha.tune.test.repository;

import com.ujacha.tune.test.domain.TestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<TestEntity, Long> {
}
