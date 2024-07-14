package com.tht.thtapis.controller.config;

import java.util.List;

import com.tht.infra.user.User;
import fixture.user.UserFixture;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

public class WithCustomMockUserSecurityContextFactory implements WithSecurityContextFactory<WithCustomMockUser> {

    @Override
    public SecurityContext createSecurityContext(WithCustomMockUser annotation) {
        String userUuid = annotation.userUuid();
        String role = annotation.role();

        User user = UserFixture.make(userUuid, role);
        UsernamePasswordAuthenticationToken token =
            new UsernamePasswordAuthenticationToken(user, "password", List.of(new SimpleGrantedAuthority(role)));
        SecurityContext context = SecurityContextHolder.getContext();
        context.setAuthentication(token);
        return context;
    }
}
