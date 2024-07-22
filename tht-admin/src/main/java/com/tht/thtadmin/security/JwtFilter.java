package com.tht.thtadmin.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Component
public class JwtFilter extends OncePerRequestFilter  {

    private final TokenProvider tokenProvider;
    private final String authHeader;

    public JwtFilter(TokenProvider tokenProvider, @Value("${jwt.auth-header-name}") String authHeader) {
        this.tokenProvider = tokenProvider;
        this.authHeader = authHeader;
    }

    @Override
    protected void doFilterInternal(final HttpServletRequest request,
        final HttpServletResponse response, final FilterChain filterChain)
        throws ServletException, IOException {

        final String token = resolveToken(request);
        final String requestURI = request.getRequestURI();

        if (StringUtils.hasText(token) && tokenProvider.validateToken(token)) {
            final Authentication auth = tokenProvider.getAuthentication(token);
            final SecurityContext securityContext = SecurityContextHolder.getContext();
            securityContext.setAuthentication(auth);

            log.debug("Security Context에 '{}' 인증 정보를 저장했습니다, uri: {}", auth.getName(), requestURI);
        }
        else {
            log.debug("유효한 JWT 토큰이 없습니다, uri: {}", requestURI);
        }

        filterChain.doFilter(request, response);

    }

    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(authHeader);

        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }

        return null;
    }

}
