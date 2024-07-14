package com.tht.thtapis.facade.user.response;

public record UserSignUpResponse(
    String accessToken,
    long accessTokenExpiresIn
) {
}
