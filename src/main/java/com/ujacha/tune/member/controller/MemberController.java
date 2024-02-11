package com.ujacha.tune.member.controller;

import com.ujacha.tune.member.dto.MemberRequestDTO;
import com.ujacha.tune.member.dto.MemberResponseDTO;
import com.ujacha.tune.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/login")
    public MemberResponseDTO.Token kakao(@RequestBody MemberRequestDTO.KakaoLogin dto) {
        return memberService.login(dto);
    }

    @PostMapping("/first")
    public String first(@RequestBody MemberRequestDTO.FirstLogin dto) {
        return memberService.firstLogin(dto);
    }

    @GetMapping("/duplicate")
    public Boolean nicknameDuplicate(@RequestParam String nickname) {
        return memberService.nicknameDuplicate(nickname);
    }

    @GetMapping("")
    public MemberResponseDTO.Check member(@RequestHeader(value = "Authorization" , required = false) String jwt) {
        return memberService.userCheck(jwt);
    }
}
