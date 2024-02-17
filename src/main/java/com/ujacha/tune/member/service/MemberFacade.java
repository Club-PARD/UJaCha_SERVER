package com.ujacha.tune.member.service;

import com.ujacha.tune.member.domain.Member;
import com.ujacha.tune.member.dto.MemberRequestDTO;
import com.ujacha.tune.member.dto.MemberResponseDTO;
import com.ujacha.tune.member.service.core.MemberService;
import com.ujacha.tune.test.service.core.TestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

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
        if (StringUtils.hasText(member.getReliableUid())) {
            return MemberResponseDTO.Check
                    .toDTO(member,
                            testService.listTestEntityToDTo(member.getId()),
                            memberService.userIdToName(member.getReliableUid()));
        }
        return MemberResponseDTO.Check.toDTO(member,
                testService.listTestEntityToDTo(member.getId()));

    }
    public boolean nicknameDuplicate(String nickname) {
        return memberService.nicknameDuplicate(nickname);
    }

    @Transactional
    public String update(MemberRequestDTO.MemberUpdate dto, String jwt) {
        Member member = memberService.findByJwt(jwt);
        if (!dto.getNickname().equals(member.getNickname())) {
            member.updateMember(dto, memberService.nameToUserId(dto.getReliableName()));
        } else {
            member.updateMember(dto.getChildAge(),memberService.nameToUserId(dto.getReliableName()));
        }
        return "업데이트 성공!";
    }

    public List<MemberResponseDTO.Check> reliableUser(String jwt) {
        List < Member > members = memberService.findByReliableUid(memberService.jwtValidate(jwt));
        return members.stream()
                .map(member -> {
                    if (StringUtils.hasText(member.getReliableUid())) {
                        return MemberResponseDTO.Check.toDTO(member,
                                testService.listTestEntityToDTo(member.getId()),
                                memberService.userIdToName(member.getReliableUid()));
                    } else {
                        return MemberResponseDTO.Check.toDTO(member,
                                testService.listTestEntityToDTo(member.getId()));
                    }
                })
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete(String jwt) {
        memberService.delete(jwt);
    }
}