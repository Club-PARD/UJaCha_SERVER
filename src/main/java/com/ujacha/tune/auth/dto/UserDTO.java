package com.ujacha.tune.auth.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class UserDTO {
    @Getter
    @Setter
    @Builder
    public static class Create{
        private String kakaoId;
        private String email;

        public static Create toDTO(final KakaoResponse response) {
            return Create.builder()
                    .kakaoId(response.getProviderId())
                    .email(response.getEmail())
                    .build();
        }
    }
    @Getter
    @Setter
    @Builder
    public static class Profile {
        private Integer childAge;
        private String nickName;
        private String reliableName;

    }

}
