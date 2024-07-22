package com.tht.thtadmin.security;


public record TokenDto(
     String accessToken,
     Long accessTokenExpiresIn,
     String userName
){

    public static TokenDto of(final String accessToken, final long accessTokenExpiresIn, final String userName) {
        return new TokenDto(accessToken, accessTokenExpiresIn, userName);
    }

}
