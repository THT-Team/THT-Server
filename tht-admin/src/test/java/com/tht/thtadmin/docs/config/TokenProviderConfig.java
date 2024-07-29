package com.tht.thtadmin.docs.config;

import com.tht.domain.entity.administrator.AdministratorService;
import com.tht.thtadmin.security.TokenProvider;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class TokenProviderConfig {

    @MockBean
    AdministratorService userService;

    @Bean
    public TokenProvider tokenProvider() {
        String key = "dakfjkasdljfkladjkahdjkfhajhfjksdalhfjkdslhfjkdsahfjkdhjkfhkjsdhfkjdshjkf";
        long tokenValiditySeconds = 3600;
        return new TokenProvider(key, tokenValiditySeconds, userService);
    }
}
