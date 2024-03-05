package com.tht.api.app.config.security;

import com.tht.api.app.facade.user.response.UserLoginResponse;
import com.tht.api.app.facade.user.response.UserSignUpResponse;

public record TokenResponse(
     String accessToken,
     Long accessTokenExpiresIn
){

    public static TokenResponse of(final String accessToken, final long accessTokenExpiresIn) {
        return new TokenResponse(accessToken, accessTokenExpiresIn);
    }

    public UserSignUpResponse toSignUpResponse() {
        return new UserSignUpResponse(accessToken, accessTokenExpiresIn);
    }

    public UserLoginResponse toLoginResponse() {
        return new UserLoginResponse(accessToken, accessTokenExpiresIn);
    }
}
