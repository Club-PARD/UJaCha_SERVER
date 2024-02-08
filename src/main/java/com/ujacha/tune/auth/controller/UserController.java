package com.ujacha.tune.auth.controller;

import com.ujacha.tune.auth.dto.KakaoResponse;
import com.ujacha.tune.auth.dto.UserDTO;
import com.ujacha.tune.auth.dto.response.UserResponseDTO;
import com.ujacha.tune.auth.entity.UserEntity;
import com.ujacha.tune.auth.service.CustomOAuth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final CustomOAuth2UserService service;

    @PatchMapping("/first/{id}")
    public ResponseEntity<UserResponseDTO> firstUser(@RequestBody UserDTO.Profile dto, @PathVariable Long id) {
        return ResponseEntity.ok(service.firstUser(dto, id));
    }
    @GetMapping("/api/userinfo")
    public ResponseEntity<String> getUserInfo(@AuthenticationPrincipal KakaoResponse principal) {
        if (principal == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(principal.getProviderId());
    }
}
