package com.tht.thtadmin.security;

import com.tht.thtadmin.security.filter.ExceptionHandlerFilter;
import com.tht.thtadmin.security.filter.JwtFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
            .csrf().disable()
            .httpBasic().disable()
            .exceptionHandling();

        return httpSecurity
            .authorizeHttpRequests(
                authorize -> authorize
                    .requestMatchers(PathRequest.toH2Console()).permitAll()
                    .requestMatchers("/error").permitAll()
                    .requestMatchers("login").permitAll()
                    .requestMatchers("create").permitAll()
                    .anyRequest().hasRole("ADMIN")

                    .and()
                    .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                    .addFilterBefore(exceptionHandlerFilter, JwtFilter.class)
            )
            .build();
    }

}
