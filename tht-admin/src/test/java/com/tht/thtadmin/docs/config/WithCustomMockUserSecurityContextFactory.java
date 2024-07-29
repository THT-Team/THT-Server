package com.tht.thtadmin.docs.config;

import com.tht.domain.entity.administrator.Administrator;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

import java.util.List;

public class WithCustomMockUserSecurityContextFactory implements WithSecurityContextFactory<WithCustomMockUser> {

    @Override
    public SecurityContext createSecurityContext(WithCustomMockUser annotation) {

        String role = annotation.role();
        String id = annotation.id();
        String password = annotation.password();
        String username = annotation.username();

        Administrator user = new Administrator(id, password, username);

        UsernamePasswordAuthenticationToken token =
            new UsernamePasswordAuthenticationToken(user, "password", List.of(new SimpleGrantedAuthority(role)));
        SecurityContext context = SecurityContextHolder.getContext();
        context.setAuthentication(token);
        return context;
    }
}
