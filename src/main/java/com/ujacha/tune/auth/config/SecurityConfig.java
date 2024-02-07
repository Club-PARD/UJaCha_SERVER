package com.ujacha.tune.auth.config;

import com.ujacha.tune.auth.service.CustomOAuth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final CustomOAuth2UserService customOAuth2UserService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf((csrf) -> csrf.disable());
        http
                .formLogin((login) -> login.disable());
        http
                .httpBasic(basic -> basic.disable());
//        http
//                .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http
//                .oauth2Login((oauth) -> oauth.defaultSuccessUrl("/login-success")
////                        .successHandler(oAuth2LoginSuccessHandler) //로그인 성공했을 때 redirection
//                        .userInfoEndpoint(userInfoEndpointConfig ->
//                                userInfoEndpointConfig.userService(userService)));
                .oauth2Login(oath2 ->
                        oath2.userInfoEndpoint(userInfoEndpointConfig ->
                                userInfoEndpointConfig.userService(customOAuth2UserService)));
        http
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/", "/oauth/**", "/login/**").permitAll()
                        .anyRequest().authenticated());

        return http.build();
    }
}
