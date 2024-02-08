package com.ujacha.tune.auth.entity;

import com.ujacha.tune.auth.dto.KakaoResponse;
import com.ujacha.tune.auth.dto.UserDTO;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userId;
    private String email;


    private String nickname;
    private int childAge;
    private String reliableName;

    public static UserEntity toEntity(KakaoResponse kakaoResponse) {
        return UserEntity.builder()
                .userId(kakaoResponse.getProviderId())
                .email(kakaoResponse.getEmail())
                .build();
    }

    public static UserEntity toEntity(UserDTO.Profile dto) {
        return UserEntity.builder()
                .childAge(dto.getChildAge())
                .nickname(dto.getNickName())
                .reliableName(dto.getReliableName())
                .build();
    }
}
