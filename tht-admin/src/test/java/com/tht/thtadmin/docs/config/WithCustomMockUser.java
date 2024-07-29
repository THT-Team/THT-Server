package com.tht.thtadmin.docs.config;

import org.springframework.security.test.context.support.WithSecurityContext;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@WithSecurityContext(factory = WithCustomMockUserSecurityContextFactory.class)
public @interface WithCustomMockUser {

    String id() default "admin-id";

    String password() default "password";

    String username() default "admin-user";

    String role() default "ADMIN";
}
