package com.ujacha.tune.auth.entity;

import com.ujacha.tune.auth.dto.KakaoResponse;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserEntity {
    @Id
    private String userId;

    private String email;

    private String nickname;

    private int childAge;

    private String reliableName;

    public static UserEntity toEntity(KakaoResponse kakaoResponse) {
        return UserEntity.builder()
                .userId(kakaoResponse.getProviderId())
                .email(kakaoResponse.getEmail())
                .nickname(kakaoResponse.getName())
                .build();
    }
}
