package com.ujacha.tune.auth.service;

import com.ujacha.tune.auth.dto.*;
import com.ujacha.tune.auth.dto.response.UserResponseDTO;
import com.ujacha.tune.auth.entity.UserEntity;
import com.ujacha.tune.auth.repository.UserRepository;
import jakarta.persistence.Id;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> oAuth2UserService = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = oAuth2UserService.loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        String userName = userRequest.getClientRegistration()
                .getProviderDetails()
                .getUserInfoEndpoint()
                .getUserNameAttributeName();

        OAuth2Attribute oAuth2Attribute = OAuth2Attribute.of(registrationId,userName, oAuth2User.getAttributes());

        Map<String, Object> memberAttribute = oAuth2Attribute.convertToMap();

        String email = (String) memberAttribute.get("email");
        Optional<UserEntity> findMember = userRepository.findByEmail(email);
        //중복 들어왓을 때 제거
        if (findMember.isEmpty()) {
            memberAttribute.put("exist", false);
            return new DefaultOAuth2User(Collections.singleton(
                    new SimpleGrantedAuthority("ROLE_USER")),
                    memberAttribute, "email");
        }

        memberAttribute.put("exist", true);
        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority("ROLE_User")),
                memberAttribute, "email"
        );


    }


}
