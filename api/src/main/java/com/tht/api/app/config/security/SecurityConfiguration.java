package com.tht.api.app.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
            .csrf().disable();

        return httpSecurity
            .authorizeHttpRequests(
                authorize -> authorize
                    .requestMatchers("/users/join/**").permitAll()
                    .requestMatchers("/login").permitAll()
                    .requestMatchers(HttpMethod.GET,  "/ideal-types").permitAll()
                    .requestMatchers(HttpMethod.GET, "/interests").permitAll()
                    .anyRequest().authenticated()
            )
            .httpBasic(Customizer.withDefaults())
            .build();
    }

//    @Bean
//    public UserDetailsService userDetailsService() {
//        UserDetails user = User.withUsername("user")
//            .password("password")
//            .roles("USER")
//            .build();
//
//        return new InMemoryUserDetailsManager(user);
//    }
}
