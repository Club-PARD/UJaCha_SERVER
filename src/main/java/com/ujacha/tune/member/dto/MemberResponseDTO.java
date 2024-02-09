package com.ujacha.tune.member.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MemberResponseDTO {
    private boolean isFirst;
    private String token;
    private int exprTime;

    public static MemberResponseDTO toDTO(boolean isFirst,
                                          String token,
                                          int exprTime) {
        return MemberResponseDTO.builder()
                .isFirst(isFirst)
                .token(token)
                .exprTime(exprTime)
                .build();
    }
}
