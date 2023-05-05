package com.tht.api.app.config.security;

import com.tht.api.app.config.utils.LogWriteUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter  {

    private final TokenProvider tokenProvider;


    @Override
    protected void doFilterInternal(final HttpServletRequest request,
        final HttpServletResponse response, final FilterChain filterChain)
        throws ServletException, IOException {

        final String token = getParseJwt(request.getHeader("Authorization"));

        if (token != null && tokenProvider.validateToken(token)) {
            // 토큰이 유효할 경우 토큰에서 Authentication 객체를 가지고 와서 SecurityContext 에 저장
            Authentication auth = tokenProvider.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(auth);
        }

        filterChain.doFilter(request, response);

    }

    private String getParseJwt(final String headerAuth) {

        LogWriteUtils.logInfo("auth token : " + headerAuth);

        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer")) {
            return headerAuth.substring(7);
        }

        return null;
    }
}
