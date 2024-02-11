package com.ujacha.tune.member.service;

import com.ujacha.tune.auth.jwt.TokenProvider;
import com.ujacha.tune.member.domain.Member;
import com.ujacha.tune.member.dto.MemberRequestDTO;
import com.ujacha.tune.member.dto.MemberResponseDTO;
import com.ujacha.tune.member.repository.MemberRepository;
import com.ujacha.tune.test.service.TestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.NoSuchElementException;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final TokenProvider tokenProvider;
    private final TestService testService;

    public void create(MemberRequestDTO.KakaoLogin dto) {
        memberRepository.save(Member.toEntity(dto));
    }

    public boolean nicknameDuplicate(String nickname) {
        return memberRepository.existsByNickname(nickname);
    }

    @Transactional
    public String firstLogin(MemberRequestDTO.FirstLogin dto) {
        if (!isFirst(dto.getUid())) {
            throw new IllegalStateException("처음 로그인한 유저가 아닙니다.: " + dto.getUid());
        }
        Member member = memberRepository.findByUid(dto.getUid())
                .orElseThrow(() -> new NoSuchElementException("해당 uid를 가진 회원을 찾을 수 없습니다.: " + dto.getUid()));
        member.updateCategory(dto);
        return token(member.getUid());
    }

    public boolean isFirst(String uid) {
        return (memberRepository.findByUid(uid).orElseThrow().getNickname() == null);
    }

    public String token(String uid) {
        return tokenProvider.create(uid);
    }

    public MemberResponseDTO.Token login(MemberRequestDTO.KakaoLogin dto) {
        if (!memberRepository.existsByUid(dto.getUid())) {
            create(dto);
        }
        if (isFirst(dto.getUid())) {
            return MemberResponseDTO.Token.toDTO(isFirst(dto.getUid()), null, 0);
        }

        return MemberResponseDTO.Token.toDTO(isFirst(dto.getUid()), token(dto.getUid()), 3600000);
    }

    public MemberResponseDTO.Check userCheck(String jwt) {
        Member member = memberRepository.findByUid(tokenProvider.validate(jwt)).orElseThrow();
        return MemberResponseDTO.Check
                .toDTO(member, testService.listTestEntityToDTo(jwt));
    }


}
