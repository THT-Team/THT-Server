package com.tht.api.app.config.security;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.mock;
import static org.mockito.BDDMockito.when;

import com.tht.api.app.entity.enums.UserRole;
import com.tht.api.app.entity.user.User;
import com.tht.api.app.service.UserService;
import com.tht.api.exception.custom.TokenNotValidateException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.util.Date;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TokenProviderTest {

    private static final String SECRET_KEY = "secretKeyIsNotShortKeyBecauseARROR8onetwothree";

    @Test
    @DisplayName("jwt 에 올바른 값이 담겨 암호화 되는지 테스트")
    void validJwtPayload_user_uuid() {

        User user = mock(User.class);
        String userUuid = "user-uuid-jwt-fake-value";
        when(user.getUserUuid()).thenReturn(userUuid);
        when(user.getUserRole()).thenReturn(UserRole.NORMAL);

        TokenProvider tokenProvider = new TokenProvider(SECRET_KEY, mock(UserService.class));
        TokenResponse tokenResponse = tokenProvider.generateJWT(user);

        Claims claims = tokenProvider.parseClaims(tokenResponse.accessToken());

        System.out.println(tokenResponse);
        System.out.println(claims);

        assertThat(claims.get("userUuid")).hasToString(user.getUserUuid());
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

        TokenProvider tokenProvider = new TokenProvider(SECRET_KEY, mock(UserService.class));

        assertThatThrownBy(() -> tokenProvider.validateToken(testToken))
            .isInstanceOf(TokenNotValidateException.class);
    }

}