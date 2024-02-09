package com.ujacha.tune.member.controller;

import com.ujacha.tune.member.dto.MemberRequestDTO;
import com.ujacha.tune.member.dto.MemberResponseDTO;
import com.ujacha.tune.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/login")
    public MemberResponseDTO kakao(@RequestBody MemberRequestDTO.KakaoLogin dto) {
        return memberService.login(dto);
    }
    @PostMapping("/first")
    public void first(@RequestBody MemberRequestDTO.FirstLogin dto) {
        memberService.firstLogin(dto);
    }
}
