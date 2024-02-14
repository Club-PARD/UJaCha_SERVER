package com.ujacha.tune.member.service;

import com.ujacha.tune.member.domain.Member;
import com.ujacha.tune.member.dto.MemberRequestDTO;
import com.ujacha.tune.member.dto.MemberResponseDTO;
import com.ujacha.tune.member.service.core.MemberService;
import com.ujacha.tune.test.service.core.TestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class MemberFacade {
    private final MemberService memberService;
    private final TestService testService;

    @Transactional
    public String firstLogin(MemberRequestDTO.FirstLogin dto) {
        if (!memberService.isFirst(dto.getUid())) {
            throw new IllegalStateException("처음 로그인한 유저가 아닙니다 : " + dto.getUid());
        }
        Member member = memberService.findByUid(dto.getUid());
        member.firstLoginCategory(dto);
        return memberService.token(member.getUid());
    }

    public MemberResponseDTO.Token login(MemberRequestDTO.KakaoLogin dto) {
        if (!memberService.existsByUid(dto.getUid())) {
            memberService.create(dto);
        }
        if (memberService.isFirst(dto.getUid())) {
            return MemberResponseDTO.Token
                    .toDTO(memberService.isFirst(dto.getUid()),
                    null,
                    0);
        }

        return MemberResponseDTO.Token
                .toDTO(memberService.isFirst(dto.getUid()),
                        memberService.token(dto.getUid()),
                        3600000);
    }

    public MemberResponseDTO.Check userCheck(String jwt) {
        Member member =memberService.findByJwt(jwt);
        return MemberResponseDTO.Check
                .toDTO(member, testService.listTestEntityToDTo(member.getId()));
    }
    public boolean nicknameDuplicate(String nickname) {
        return memberService.nicknameDuplicate(nickname);
    }

}
