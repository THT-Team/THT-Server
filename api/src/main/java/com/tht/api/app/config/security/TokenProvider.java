package com.tht.api.app.config.security;

import com.tht.api.app.entity.user.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TokenProvider {

    @Value("${jwt.secret-key}")
    private static String jwtSecretKey;
    private static final long ACCESS_TOKEN_VALID_PERIOD = 1000L * 60 * 30;
    private static final long REFRESH_TOKEN_VALID_PERIOD = 1000L * 60 * 60 * 24 * 8;


    public static String generateJWT(final User userInfo) {
        final Date now = new Date();
        final Date accessTokenExpireIn = new Date(now.getTime() + ACCESS_TOKEN_VALID_PERIOD);
        final Date refreshTokenExpireIn = new Date(now.getTime() + REFRESH_TOKEN_VALID_PERIOD);

        final String accessToken = Jwts.builder()
            .setSubject("authorization") // 토큰 용도
            .claim("number", userInfo.getPhoneNumber()) // Claims 설정
            .claim("role", userInfo.getUserRole())
            .setExpiration(accessTokenExpireIn) // 토큰 만료 시간 설정
            .signWith(Keys.hmacShaKeyFor(
                jwtSecretKey.getBytes(StandardCharsets.UTF_8))) // HS256과 Key로 Sign
            .compact(); // 토큰 생성

        return null;
    }
}
