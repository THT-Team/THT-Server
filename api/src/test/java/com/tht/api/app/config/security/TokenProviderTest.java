package com.tht.api.app.config.security;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.mock;
import static org.mockito.BDDMockito.when;

import com.tht.api.app.entity.enums.UserRole;
import com.tht.api.app.entity.user.User;
import io.jsonwebtoken.Claims;
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

        TokenProvider tokenProvider = new TokenProvider(SECRET_KEY);
        TokenResponse tokenResponse = tokenProvider.generateJWT(user);

        Claims claims = tokenProvider.parseClaims(tokenResponse.accessToken());

        System.out.println(tokenResponse);
        System.out.println(claims);

        assertThat(claims.get("userUuid")).hasToString(user.getUserUuid());
        assertThat(claims.get("role")).hasToString(user.getUserRole().name());

    }

    //시간초과 등 만료 케이스 테스트
}