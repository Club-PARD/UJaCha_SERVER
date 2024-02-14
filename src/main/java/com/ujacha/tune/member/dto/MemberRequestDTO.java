package com.ujacha.tune.member.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberRequestDTO {
    @Getter
    @Setter
    public static class KakaoLogin {
        private String email;
        private String uid;
    }
    @Getter
    @Setter
    public static class FirstLogin {
        private String nickname;
        private Integer childAge;
        private String uid;
    }
    @Getter
    @Setter
    public static class MemberUpdate {
        private String nickname;
        private String reliableUid;
        private Integer childAge;
    }
}
