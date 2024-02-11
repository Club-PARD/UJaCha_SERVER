package com.ujacha.tune.auth.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
public class TokenProvider {
    private static final Key SECURITY_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS512);

    public String create(String Uid){
        Date exprTime = Date.from(Instant.now().plus(1, ChronoUnit.HOURS));
        return Jwts.builder()
                .signWith(SECURITY_KEY)
                .setSubject(Uid)
                .setIssuedAt(new Date())
                .setExpiration(exprTime)
                .compact();
    }
    public String validate(String token){
        if (!StringUtils.hasText(token)) {
            throw new IllegalArgumentException("Token must not be null or empty");
        }
        String token1 = token;
        if(token1.startsWith("Bearer ")) {
            token1 = token1.substring(7);
        }
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(SECURITY_KEY)
                .build()
                .parseClaimsJws(token1)
                .getBody();
        return claims.getSubject();
    }
}
