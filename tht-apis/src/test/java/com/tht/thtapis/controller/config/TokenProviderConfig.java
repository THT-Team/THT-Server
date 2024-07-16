package com.tht.thtapis.controller.config;

import com.tht.domain.auth.UserAuthService;
import com.tht.thtapis.security.TokenProvider;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class TokenProviderConfig {

    @MockBean
    UserAuthService userAuthService;

    @Bean
    public TokenProvider tokenProvider() {
        String key = "dakfjkasdljfkladjkahdjkfhajhfjksdalhfjkdslhfjkdsahfjkdhjkfhkjsdhfkjdshjkf";
        return new TokenProvider(key, userAuthService);
    }
}
