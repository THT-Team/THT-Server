package com.tht.api.app.config.security;

import com.tht.api.app.config.security.filter.ExceptionHandlerFilter;
import com.tht.api.app.config.security.filter.JwtFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final JwtFilter jwtFilter;
    private final ExceptionHandlerFilter exceptionHandlerFilter;
    private final HttpRequestEndpointChecker endpointChecker;


    private static final String[] PERMIT_URL_ARRAY = {
        /* swagger v2 */
        "/v2/api-docs",
        "/swagger-resources",
        "/swagger-resources/**",
        "/configuration/ui",
        "/configuration/security",
        "/swagger-ui.html",
        "/webjars/**",
        /* swagger v3 */
        "/v3/api-docs/**",
        "/swagger-ui/**",
        "docs/**",
        "/",    // root 와 현결해놓음
        /* 회원가입 */
        "/users/join/**",
        "/users/login/**",
        "/all/talk-keyword",
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
            .csrf().disable()
            .httpBasic().disable()
            .exceptionHandling()
            .authenticationEntryPoint(new MyAuthenticationEntryPoint(endpointChecker))
            .accessDeniedHandler(new MyAccessDeniedHandler(endpointChecker));


        return httpSecurity
            .authorizeHttpRequests(
                authorize -> authorize
                    .requestMatchers(PERMIT_URL_ARRAY).permitAll()
                    .requestMatchers(HttpMethod.GET, "/ideal-types").permitAll()
                    .requestMatchers(HttpMethod.GET, "/interests").permitAll()
                    .anyRequest().authenticated()

                    .and()
                    .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                    .addFilterBefore(exceptionHandlerFilter, JwtFilter.class)
            )
            .build();
    }

}
