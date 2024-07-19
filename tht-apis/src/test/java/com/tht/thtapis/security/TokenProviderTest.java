package com.tht.thtapis.security;

import com.tht.infra.user.User;
import com.tht.enums.user.UserRole;
import com.tht.infra.user.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

class TokenProviderTest {

    private static final String SECRET_KEY = "secretKeyIsNotShortKeyBecauseARROR8onetwothree";

    @Test
    @DisplayName("jwt 에 올바른 값이 담겨 암호화 되는지 테스트")
    void validJwtPayload_user_uuid() {

        User user = Mockito.mock(User.class);
        String userUuid = "user-uuid-jwt-fake-value";
        Mockito.when(user.getUserUuid()).thenReturn(userUuid);
        Mockito.when(user.getUserRole()).thenReturn(UserRole.NORMAL);

        TokenProvider tokenProvider = new TokenProvider(SECRET_KEY, Mockito.mock(UserService.class));
        TokenDto tokenDto = tokenProvider.generateJWT(user);

        Claims claims = tokenProvider.parseClaims(tokenDto.accessToken());

        System.out.println(tokenDto);
        System.out.println(claims);

        assertThat(claims.get("uuid")).hasToString(user.getUserUuid());
        assertThat(claims.get("role")).hasToString(user.getUserRole().name());

    }

    @Test
    @DisplayName("jwt 유효성 검증 테스트 - 유효하지 않은 token")
    void notValidJwt() {

        String testToken = Jwts.builder()
            .setSubject("authorization")
            .setExpiration(new Date(new Date().getTime()))
            .signWith(Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET_KEY)),
                SignatureAlgorithm.HS256)
            .compact();

        TokenProvider tokenProvider = new TokenProvider(SECRET_KEY, Mockito.mock(UserService.class));

        Assertions.assertThatThrownBy(() -> tokenProvider.validateToken(testToken))
            .isInstanceOf(TokenNotValidateException.class);
    }

}