package com.project.preset.util.security.jwt;

import com.project.preset.member.domain.type.RoleType;
import com.project.preset.properties.jwt.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component // Bean 등록(@Bean과 비교 -> 설정 파일 같은 데서.) 어차피 이 클래스도 우리가 만든 거니까 알아서 빈 만들어 달라고 여기서 바로 선언하면 편함.
public final class JwtProvider {
    private final Key secretKey; // 예전: String 그대로 -> 지금: Key라는 타입
    private final Long expiration;

    public JwtProvider(JwtProperties jwtProperties) {
        // Key
        System.out.println("jwtProperties.secret(): " + jwtProperties.secret());
        byte[] keyBytes = Decoders.BASE64.decode(jwtProperties.secret());
        secretKey = Keys.hmacShaKeyFor(keyBytes);

        expiration = jwtProperties.expiration();
    }

    public String generate(String username) {
        return generate(username, RoleType.USER_ROLE);
    }

    public String generate(String username, RoleType role) {
        Claims claims = Jwts.claims().setSubject(username);

        Date now = new Date();
        Date expiration = new Date(now.getTime() + this.expiration);

        claims.put("authority", role); // Payload의 한 부분.

        String jwt = Jwts.builder() // Jwts
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();

        return jwt;
    }
}
