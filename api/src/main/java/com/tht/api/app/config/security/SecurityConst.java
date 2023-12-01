package com.tht.api.app.config.security;

import org.springframework.context.annotation.Configuration;

@Configuration
public abstract class SecurityConst {

    public static final String AUTH_HEADER_NAME = "Authorization";

    private SecurityConst(){
        throw new NullPointerException("SecurityConst class is const abstract class");
    }
}
