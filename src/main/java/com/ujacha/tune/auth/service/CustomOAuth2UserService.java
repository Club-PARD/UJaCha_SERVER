package com.ujacha.tune.auth.service;

import com.ujacha.tune.auth.dto.CustomOAuth2User;
import com.ujacha.tune.auth.dto.KakaoResponse;
import com.ujacha.tune.auth.dto.OAuth2Response;
import com.ujacha.tune.auth.entity.UserEntity;
import com.ujacha.tune.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) {
        OAuth2User oAuth2User = super.loadUser(userRequest);
//        System.out.println(oAuth2User.getAttributes());

        KakaoResponse oAuth2Response = new KakaoResponse(oAuth2User.getAttributes());
//중복 들어왓을 때 제거
        if (!userRepository.existsById(oAuth2Response.getProviderId())) {
            userRepository.save(UserEntity.toEntity(oAuth2Response));
        }

        return new CustomOAuth2User(oAuth2Response);
    }
}
