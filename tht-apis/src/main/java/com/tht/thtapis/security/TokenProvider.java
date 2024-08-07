package com.tht.thtapis.security;

import com.tht.domain.entity.user.User;
import com.tht.domain.entity.user.service.UserService;
import com.tht.thtcommonutils.utils.LogWriteUtils;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.security.Key;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Stream;

@Component
public class TokenProvider {

    private static final long ACCESS_TOKEN_VALID_PERIOD =  1000L * 60 * 60 * 24 * 7; //7일

    private final Key jwtSecretKey;
    private final UserService userService;

    public TokenProvider(@Value("${jwt.secret-key}") String secretKey,
        final UserService userService) {

        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        jwtSecretKey = Keys.hmacShaKeyFor(keyBytes);

        this.userService = userService;
    }

    public TokenDto generateJWT(final User userInfo) {
        final Date now = new Date();
        final Date accessTokenExpireIn = new Date(now.getTime() + ACCESS_TOKEN_VALID_PERIOD);

        final String accessToken = Jwts.builder()
            .setSubject("authorization")
            .claim("uuid", userInfo.getUserUuid())
            .claim("role", userInfo.getUserRole())
            .setIssuedAt(now)
            .setExpiration(accessTokenExpireIn)
            .signWith(jwtSecretKey, SignatureAlgorithm.HS256)
            .compact();

        return TokenDto.of(accessToken,accessTokenExpireIn.getTime(), userInfo.getUserUuid());
    }

    public boolean validateToken(final String token) {
        try {
            Jwts.parserBuilder().setSigningKey(jwtSecretKey).build().parseClaimsJws(token);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            LogWriteUtils.logInfo(String.format("exception : %s, message : 잘못된 JWT 서명입니다.", e.getClass().getName()));
            throw TokenNotValidateException.InvalidSecuritySign(e);

        } catch (ExpiredJwtException e) {
            LogWriteUtils.logInfo(String.format("exception : %s, message : 만료된 JWT 토큰입니다.", e.getClass().getName()));
            throw TokenNotValidateException.ExpiredToken(e);

        } catch (UnsupportedJwtException e) {
            LogWriteUtils.logInfo(String.format("exception : %s, message : 지원되지 않는 JWT 토큰입니다.", e.getClass().getName()));
            throw TokenNotValidateException.UnsupportedJwt(e);

        } catch (IllegalArgumentException e) {
            LogWriteUtils.logInfo(String.format("exception : %s, message : JWT 토큰이 잘못되었습니다.", e.getClass().getName()));
            throw TokenNotValidateException.IllegalArgumentJwt(e);
        }
    }

    public Claims parseClaims(final String accessToken) {
        try {
            return Jwts.parserBuilder().setSigningKey(jwtSecretKey).build()
                .parseClaimsJws(accessToken)
                .getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }

    public Authentication getAuthentication(final String token) {
        // 토큰 복호화
        Claims claims = parseClaims(token);
        LogWriteUtils.logInfo("token_claims : " + claims.toString());

        if (claims.get("role") == null) {
            throw new BadCredentialsException("권한 정보가 없는 토큰입니다.");
        }

        // 클레임에서 권한 정보 가져오기
        final Collection<? extends GrantedAuthority> authorities = Stream.of(
                claims.get("role").toString())
            .map(SimpleGrantedAuthority::new)
            .toList();

        final String userUuid = claims.get("uuid").toString();
        final User user = userService.findByUserUuidForAuthToken(userUuid);

        return new UsernamePasswordAuthenticationToken(user, userUuid, authorities);
    }

    public String getParseJwt(final String headerAuth) {

        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer")) {
            return headerAuth.substring(7);
        }

        return null;
    }
}
