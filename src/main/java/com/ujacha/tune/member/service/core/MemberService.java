package com.ujacha.tune.member.service.core;

import com.ujacha.tune.auth.jwt.TokenProvider;
import com.ujacha.tune.member.domain.Member;
import com.ujacha.tune.member.dto.MemberRequestDTO;
import com.ujacha.tune.member.repository.MemberRepository;
import com.ujacha.tune.test.service.core.TestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final TokenProvider tokenProvider;

    public void create(MemberRequestDTO.KakaoLogin dto) {
        memberRepository.save(Member.toEntity(dto));
    }

    public boolean nicknameDuplicate(String nickname) {
        return memberRepository.existsByNickname(nickname);
    }



    public boolean isFirst(String uid) {
        return (findByUid(uid).getNickname() == null);
    }

    public String token(String uid) {
        return tokenProvider.create(uid);
    }

    public Member findByUid(String uid) {
        return memberRepository.findByUid(uid)
                .orElseThrow(() -> new NoSuchElementException("해당 uid를 가진 회원을 찾을 수 없습니다.: " + uid));
    }
    public Member findByName(String name) {
        return memberRepository.findByNickname(name)
                .orElseThrow(() -> new NoSuchElementException("해당 nickName를 가진 회원을 찾을 수 없습니다.: " + name));
    }

    public Member findByJwt(String jwt) {
        return memberRepository.findByUid(tokenProvider.validate(jwt))
                .orElseThrow(() -> new NoSuchElementException("해당 uid 가진 회원을 찾을 수 없습니다.: "+ tokenProvider.validate(jwt)));
    }

    public String userIdToName(String uid) {
        return findByUid(uid).getNickname();
    }

    public String nameToUserId(String name) {
        return findByName(name).getNickname();
    }

    public boolean existsByUid(String uid) {
        return memberRepository.existsByUid(uid);
    }
}
