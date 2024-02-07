package com.ujacha.tune.auth.dto;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public class KakaoResponse implements OAuth2Response {

    private final Map<String, Object> attribute;
    private Long id;

    public KakaoResponse(Map<String,Object> attribute) {
        this.attribute =(Map<String,Object>) attribute.get("kakao_account");
        this.id = (Long) attribute.get("id");
    }

    @Override
    public String getProviderId() {
        return id.toString();
    }

    @Override
    public String getEmail() {
        return attribute.get("email").toString();
    }

    @Override
    public String getName() {
       return  ((Map<String, Object>) attribute.get("profile")).get("nickname").toString();
    }

}
