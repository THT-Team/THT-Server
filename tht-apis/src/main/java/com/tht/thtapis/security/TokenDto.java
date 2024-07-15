package com.tht.thtapis.security;


public record TokenDto(
     String accessToken,
     Long accessTokenExpiresIn,
     String userUuid
){

    public static TokenDto of(final String accessToken, final long accessTokenExpiresIn, final String userUuid) {
        return new TokenDto(accessToken, accessTokenExpiresIn, userUuid);
    }

}
