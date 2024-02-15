package com.ujacha.tune.member.dto;

import com.ujacha.tune.member.domain.Member;

import com.ujacha.tune.test.dto.TestResponseDTO;
import lombok.*;

import java.util.List;

@RequiredArgsConstructor
public class MemberResponseDTO {
    @Getter
    @Setter
    @Builder
    public static class Token{
        private boolean isFirst;
        private String token;
        private int exprTime;
        public static Token toDTO(boolean isFirst,
                                              String token,
                                              int exprTime) {
            return Token.builder()
                    .isFirst(isFirst)
                    .token(token)
                    .exprTime(exprTime)
                    .build();
        }
    }

    @Getter
    @Setter
    @Builder
    public static class Check {
        private String uid;
        private String email;
        private String nickname;
        private String reliableName;
        private Integer childAge;
        private List<TestResponseDTO.Response> test;

        public static Check toDTO(Member member, List<TestResponseDTO.Response> test, String reliableName) {
            return Check.builder()
                    .uid(member.getUid())
                    .email(member.getEmail())
                    .nickname(member.getNickname())
                    .reliableName(reliableName)
                    .childAge(member.getChildAge())
                    .test(test)
                    .build();
        }
        public static Check toDTO(Member member, List<TestResponseDTO.Response> test) {
            return Check.builder()
                    .uid(member.getUid())
                    .email(member.getEmail())
                    .nickname(member.getNickname())
                    .reliableName(member.getReliableUid())
                    .childAge(member.getChildAge())
                    .test(test)
                    .build();
        }
    }
}
