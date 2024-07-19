package com.tht.thtapis.controller.config;

import com.tht.thtapis.security.TokenProvider;
import com.tht.infra.user.service.UserService;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class TokenProviderConfig {

    @MockBean
    UserService userService;

    @Bean
    public TokenProvider tokenProvider() {
        String key = "dakfjkasdljfkladjkahdjkfhajhfjksdalhfjkdslhfjkdsahfjkdhjkfhkjsdhfkjdshjkf";
        return new TokenProvider(key, userService);
    }
}
