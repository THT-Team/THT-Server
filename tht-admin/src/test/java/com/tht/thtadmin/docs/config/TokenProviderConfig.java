package com.tht.thtadmin.docs.config;

import com.tht.domain.entity.administrator.AdministratorService;
import com.tht.thtadmin.security.TokenProvider;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class TokenProviderConfig {

    @MockitoBean
    AdministratorService userService;

    @Bean
    public TokenProvider tokenProvider() {
        String key = "dakfjkasdljfkladjkahdjkfhajhfjksdalhfjkdslhfjkdsahfjkdhjkfhkjsdhfkjdshjkf";
        long tokenValiditySeconds = 3600;
        return new TokenProvider(key, tokenValiditySeconds, userService);
    }
}
