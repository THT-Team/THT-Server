package com.tht.thtapis.facade.user.response;

public record UserLoginResponse(
    String accessToken,
    long accessTokenExpiresIn
) {
}
