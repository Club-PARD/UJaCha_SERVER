package com.ujacha.tune.member.repository;

import com.ujacha.tune.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,Long> {
    Optional<Member> findByUid(String uid);
    Optional<Member> findByNickname(String nickname);

    boolean existsByUid(String uid);

    boolean existsByNickname(String nickname);

}
