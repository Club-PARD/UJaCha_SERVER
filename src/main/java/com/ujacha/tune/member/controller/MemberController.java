package com.ujacha.tune.member.controller;

import com.ujacha.tune.member.dto.MemberRequestDTO;
import com.ujacha.tune.member.dto.MemberResponseDTO;
import com.ujacha.tune.member.service.MemberFacade;
import com.ujacha.tune.member.service.core.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(name = "member 컨트롤러" , description = "멤버 로그인과 조회 컨트롤러입니다.")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberController {
    private final MemberFacade memberFacade;

    @Operation(summary = "카카오 로그인 정보를 받는 api",
            description = "카카오로서부터 제공된 EMail과 userId를 보내주면 token, exprTime(끝날 시간 : 1시간)과 처음 로그인했는지 안했는지 반환해준다.(처음로그인일 경우 true)")
    @PostMapping("/login")
    public ResponseEntity<MemberResponseDTO.Token> kakao(@RequestBody MemberRequestDTO.KakaoLogin dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(memberFacade.login(dto));
    }

    @Operation(summary = "우리 서비스 회원가입",
            description = "처음 로그인을 한 유저라면 회원가입 페이지로 가서 받는 api, (nickname, reliableName, childAge, uid를 받고 token값만 String으로 반환")
    @PostMapping("/first")
    public ResponseEntity<String> first(@RequestBody MemberRequestDTO.FirstLogin dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(memberFacade.firstLogin(dto));
    }
    @Operation(summary = "닉네임 중복 확인 api",
            description = "닉네임을 RequestParam으로 보내면 중복되면 true, 중복이 안된다면 false를 반환한다.")
    @GetMapping("/duplicate")
    public ResponseEntity<Boolean> nicknameDuplicate(@RequestParam String nickname) {
        return ResponseEntity.ok(memberFacade.nicknameDuplicate(nickname));
    }

    @Operation(summary = "회원 조회",
            description = "JWT로 회원을 조회하여 회원정보와 그 회원이 가지고 있는 Test결과를 반환한다.")
    @GetMapping("")
    public ResponseEntity<MemberResponseDTO.Check> member(@RequestHeader(value = "Authorization", required = false) final String jwt) {
        return ResponseEntity.ok(memberFacade.userCheck(jwt));
    }

    @Operation(summary = "회원 업데이트",
            description = "JWT로 회원을 조회하여 회원정보와 Body를 이용하여 회원을 업데이트 합니다.")
    @PatchMapping("/update")
    public ResponseEntity<String> updateMember(@RequestBody final MemberRequestDTO.MemberUpdate dto,
            @RequestHeader(value = "Authorization" , required = false) final String jwt) {
        return ResponseEntity.ok(memberFacade.update(dto,jwt));
    }

    @Operation(summary = "믿음직한 사람",
            description = "나를 믿음직한 사람으로 등록한 사람들의 테스트 결과를 볼 수 있음")
    @GetMapping("/reliable")
    public List<MemberResponseDTO.Check> reliableName(@RequestHeader(value = "Authorization", required = false) final String jwt) {
        return memberFacade.reliableUser(jwt);
    }

    @DeleteMapping("/delete")
    public String delete(@RequestHeader(value = "Authorization") final String jwt) {
        memberFacade.delete(jwt);
        return "삭제 완료!";
    }
}
