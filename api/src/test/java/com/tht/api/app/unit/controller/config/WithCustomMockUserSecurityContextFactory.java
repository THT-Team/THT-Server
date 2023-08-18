package com.tht.api.app.unit.controller.config;

import com.tht.api.app.entity.user.User;
import com.tht.api.app.unit.fixture.user.UserFixture;
import java.util.List;
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
