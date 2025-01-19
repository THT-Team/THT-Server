package com.tht.thtapis.controller.config;

import com.tht.thtapis.security.TokenProvider;
import com.tht.domain.entity.user.service.UserService;
import org.springframework.boot.test.context.TestConfiguration;
import org.mockito.Mock;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class TokenProviderConfig {

    @Mock
    UserService userService;

    @Bean
    public TokenProvider tokenProvider() {
        String key = "dakfjkasdljfkladjkahdjkfhajhfjksdalhfjkdslhfjkdsahfjkdhjkfhkjsdhfkjdshjkf";
        return new TokenProvider(key, userService);
    }
}
