package com.tht.thtadmin.security;

import com.tht.thtadmin.security.filter.ExceptionHandlerFilter;
import com.tht.thtadmin.security.filter.JwtFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final JwtFilter jwtFilter;
    private final ExceptionHandlerFilter exceptionHandlerFilter;

    private static final String[] SWAGGER_URL_ARRAY = {
        /* swagger v3 */
        "/v3/api-docs/**",
        "/swagger-ui/**",
        "docs/**",
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
            .csrf(AbstractHttpConfigurer::disable)
            .cors(withDefaults())
            .httpBasic(AbstractHttpConfigurer::disable)
            .exceptionHandling(withDefaults());

        return httpSecurity
            .authorizeHttpRequests(
                authorize -> authorize
                    .requestMatchers(PathRequest.toH2Console()).permitAll()
                    .requestMatchers("/error").permitAll()
                    .requestMatchers("login").permitAll()
                    .requestMatchers("create").permitAll()
                    .requestMatchers(SWAGGER_URL_ARRAY).permitAll()
                    .anyRequest().hasAuthority("ADMIN")
            )
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
            .addFilterBefore(exceptionHandlerFilter, JwtFilter.class)
            .build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:5173"));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
