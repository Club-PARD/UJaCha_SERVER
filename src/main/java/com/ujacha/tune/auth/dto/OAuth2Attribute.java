package com.ujacha.tune.auth.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

@ToString
@Builder
@Getter
public class OAuth2Attribute {
    private Map<String, Object> attributes;
    private String attributeKey;
    private String email;
    private String name;
    private String provider;

    public static OAuth2Attribute of(String provider, String attributeKey, Map<String, Object> attributes) {
        switch (provider) {
            case "kakao":
                return ofKaKao(provider, "email", attributes);
            default:
                throw new RuntimeException();
        }
    }

    private static OAuth2Attribute ofKaKao(String provider, String attributeKey, Map<String, Object> attributes) {
        Map<String, Object> kakaoAcount = (Map<String, Object>) attributes.get("kakao_account");
        Map<String, Object> kakaoProfile = (Map<String, Object>) kakaoAcount.get("profile");

        return OAuth2Attribute.builder()
                .email((String) kakaoAcount.get("email"))
                .provider(provider)
                .attributes(kakaoAcount)
                .attributeKey(attributeKey)
                .build();

    }

    public Map<String,Object> convertToMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", attributeKey);
        map.put("key", attributeKey);
        map.put("email", email);
        map.put("provider", provider);
        return map;
    }
}
