package com.ujacha.tune.test.repository;

import com.ujacha.tune.member.domain.Member;
import com.ujacha.tune.test.domain.TestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TestRepository extends JpaRepository<TestEntity, Long> {
    List<TestEntity> findByMemberId(Long memberId);

    Boolean existsByDateAndMember(LocalDate date, Member member);

    void deleteByDateAndMember(LocalDate date, Member member);
}
