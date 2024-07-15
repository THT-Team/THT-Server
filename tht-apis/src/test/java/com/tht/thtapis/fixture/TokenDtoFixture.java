package com.tht.thtapis.fixture;

import com.tht.thtapis.security.TokenDto;

public class TokenDtoFixture {

    private static final String accessToken = "accessToken";
    private static final Long accessTokenExpiresIn = 21312309L;
    private static final String userUuid = "user-uuid";

    public static TokenDto createTokenDto() {
        return TokenDto.of(accessToken, accessTokenExpiresIn, userUuid);
    }
}
