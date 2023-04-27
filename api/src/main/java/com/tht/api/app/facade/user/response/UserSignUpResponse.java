package com.tht.api.app.facade.user.response;

public record UserSignUpResponse(
    String accessToken,
    long accessTokenExpiresIn
) {
}
