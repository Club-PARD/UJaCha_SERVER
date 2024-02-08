package com.ujacha.tune.auth.config;

import com.ujacha.tune.auth.service.CustomOAuth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    //    private final MyAuthenticationSuccessHandler oAuth2LoginSuccessHandler;
//    private final JwtAuthFilter jwtAuthFilter;
    private final CustomOAuth2UserService customOAuth2UserService;
//    private final MyAuthenticationFailureHandler oAuth2LoginFailureHandler;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable);
//        http
//                .cors();
        http
                .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http
//                .oauth2Login((oauth) -> oauth.defaultSuccessUrl("/login-success")
////                        .successHandler(oAuth2LoginSuccessHandler) //로그인 성공했을 때 redirection
//                        .userInfoEndpoint(userInfoEndpointConfig ->
//                                userInfoEndpointConfig.userService(userService)));
                .oauth2Login(oath2 ->
                        oath2.userInfoEndpoint(userInfoEndpointConfig ->
                                userInfoEndpointConfig.userService(customOAuth2UserService))
//                                .failureHandler(oAuth2LoginFailureHandler)
//                                .successHandler(oAuth2LoginSuccessHandler)
                );
        http
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/", "/oauth/**", "/login/**", "/token/**").permitAll()
                        .anyRequest().authenticated());

        return http.build();
    }
}
