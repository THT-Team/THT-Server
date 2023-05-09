package com.tht.api.app.facade.user.response;

public record UserLoginResponse(
    String accessToken,
    long accessTokenExpiresIn
) {
}
