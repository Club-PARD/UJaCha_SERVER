package com.ujacha.tune.member.service;

import com.ujacha.tune.auth.jwt.TokenProvider;
import com.ujacha.tune.member.domain.Member;
import com.ujacha.tune.member.dto.MemberRequestDTO;
import com.ujacha.tune.member.dto.MemberResponseDTO;
import com.ujacha.tune.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final TokenProvider tokenProvider;

    public void create(MemberRequestDTO.KakaoLogin dto) {
        memberRepository.save(Member.toEntity(dto));
    }

    @Transactional
    public void firstLogin(MemberRequestDTO.FirstLogin dto) {
        if (!isFirst(dto.getUid())) {
            throw new IllegalStateException("This is not the first login for UID: " + dto.getUid());
        }
        Member member = memberRepository.findByUid(dto.getUid())
                .orElseThrow(NullPointerException::new);
        member.updateCategory(dto);
    }

    public boolean isFirst(String uid) {
        return (memberRepository.findByUid(uid).orElseThrow().getNickname() == null);
    }

    public String token(String email) {
        return tokenProvider.create(email);
    }

    public MemberResponseDTO login(MemberRequestDTO.KakaoLogin dto) {
        if (!memberRepository.existsByUid(dto.getUid())) {
            create(dto);
        }
        if (isFirst(dto.getUid())) {
            return MemberResponseDTO.toDTO(isFirst(dto.getUid()), null, 0);
        }

        return MemberResponseDTO.toDTO(isFirst(dto.getUid()), token(dto.getEmail()), 3600000);
    }
}
