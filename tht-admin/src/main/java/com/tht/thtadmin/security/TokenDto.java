package com.tht.thtadmin.security;


public record TokenDto(
     String accessToken,
     Long accessTokenExpiresIn,
     String username
){

    public static TokenDto of(final String accessToken, final long accessTokenExpiresIn, final String username) {
        return new TokenDto(accessToken, accessTokenExpiresIn, username);
    }

}
