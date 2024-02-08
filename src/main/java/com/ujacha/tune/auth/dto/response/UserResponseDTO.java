package com.ujacha.tune.auth.dto.response;

import com.ujacha.tune.auth.entity.UserEntity;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponseDTO {
    private String userId;

    private String email;

    private String nickname;

    private int childAge;

    private String reliableName;

    public static UserResponseDTO toDTO(UserEntity user) {
        return UserResponseDTO.builder()
                .userId(user.getUserId())
                .email(user.getEmail())
                .nickname(user.getNickname())
                .childAge(user.getChildAge())
                .reliableName(user.getReliableName())
                .build();
    }
}
