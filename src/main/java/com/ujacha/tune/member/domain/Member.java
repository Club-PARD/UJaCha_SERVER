package com.ujacha.tune.member.domain;

import com.ujacha.tune.member.dto.MemberRequestDTO;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String uid;
    @Column(nullable = false, unique = true)
    private String email;

    private String nickname;
    private String reliableName;
    private Integer childAge;

    public static Member toEntity(MemberRequestDTO.KakaoLogin dto) {
        return Member.builder()
                .uid(dto.getUid())
                .email(dto.getEmail())
                .build();
    }

}
