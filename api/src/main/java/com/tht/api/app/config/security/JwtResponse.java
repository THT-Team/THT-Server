package com.tht.api.app.config.security;

public record JwtResponse (
     String accessToken,
     String refreshToken,
     long accessTokenExpiresIn,
     long refreshTokenExpiresIn
){ }
