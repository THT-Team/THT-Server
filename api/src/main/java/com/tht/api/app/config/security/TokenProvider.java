package com.tht.api.app.config.security;

import com.tht.api.app.config.utils.LogWriteUtils;
import com.tht.api.app.entity.user.User;
import com.tht.api.app.service.UserService;
import com.tht.api.exception.custom.TokenNotValidateException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class TokenProvider {

//    private static final long ACCESS_TOKEN_VALID_PERIOD =  1000L * 60 * 90;

    private static final long ACCESS_TOKEN_VALID_PERIOD =  1000L * 60;

    private final Key jwtSecretKey;
    private final UserService userService;

    public TokenProvider(@Value("${jwt.secret-key}") String secretKey,
        final UserService userService) {

        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        jwtSecretKey = Keys.hmacShaKeyFor(keyBytes);

        this.userService = userService;
    }

    public TokenResponse generateJWT(final User userInfo) {
        final Date now = new Date();
        final Date accessTokenExpireIn = new Date(now.getTime() + ACCESS_TOKEN_VALID_PERIOD);

        final String accessToken = Jwts.builder()
            .setSubject("authorization")
            .claim("userUuid", userInfo.getUserUuid())
            .claim("role", userInfo.getUserRole())
            .claim("username", userInfo.getUsername())
            .setExpiration(accessTokenExpireIn)
            .signWith(jwtSecretKey, SignatureAlgorithm.HS256)
            .compact();

        return TokenResponse.of(accessToken,accessTokenExpireIn.getTime());
    }

    public boolean validateToken(final String token) {
        try {
            Jwts.parserBuilder().setSigningKey(jwtSecretKey).build().parseClaimsJws(token);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            LogWriteUtils.logInfo(String.format("exception : %s, message : 잘못된 JWT 서명입니다.", e.getClass().getName()));
            throw new TokenNotValidateException("잘못된 JWT 서명입니다.", e);

        } catch (ExpiredJwtException e) {
            LogWriteUtils.logInfo(String.format("exception : %s, message : 만료된 JWT 토큰입니다.", e.getClass().getName()));
            throw new TokenNotValidateException("만료된 JWT 토큰입니다.", e);

        } catch (UnsupportedJwtException e) {
            LogWriteUtils.logInfo(String.format("exception : %s, message : 지원되지 않는 JWT 토큰입니다.", e.getClass().getName()));
            throw new TokenNotValidateException("지원되지 않는 JWT 토큰입니다.", e);

        } catch (IllegalArgumentException e) {
            LogWriteUtils.logInfo(String.format("exception : %s, message : JWT 토큰이 잘못되었습니다.", e.getClass().getName()));
            throw new TokenNotValidateException("JWT 토큰이 잘못되었습니다.", e);
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

        final String userUuid = claims.get("userUuid").toString();
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
