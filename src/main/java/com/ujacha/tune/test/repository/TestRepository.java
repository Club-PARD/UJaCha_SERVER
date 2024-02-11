package com.ujacha.tune.test.repository;

import com.ujacha.tune.test.domain.TestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TestRepository extends JpaRepository<TestEntity, Long> {
    List<TestEntity> findByMemberId(Long memberId);
}
