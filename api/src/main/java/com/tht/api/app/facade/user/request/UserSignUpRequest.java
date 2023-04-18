package com.tht.api.app.facade.user.request;

import com.tht.api.app.entity.user.Gender;
import com.tht.api.app.entity.user.User;
import lombok.Builder;
import org.springframework.util.StringUtils;

@Builder
public record UserSignUpRequest (
        String username,
        String password,
        String name,
        String email,
        String phoneNumber,
        Gender gender,
        Gender preferGender

        // todo : add address
) {
    public void validate() {
        if (!StringUtils.hasText(username)) {
            throw new IllegalArgumentException("username is empty");
        }

        if (!StringUtils.hasText(password)) {
            throw new IllegalArgumentException("password is empty");
        }

        if (!StringUtils.hasText(name)) {
            throw new IllegalArgumentException("name is empty");
        }

        if (!StringUtils.hasText(email)) {
            throw new IllegalArgumentException("email is empty");
        }

        if (!StringUtils.hasText(phoneNumber)) {
            throw new IllegalArgumentException("phoneNumber is empty");
        }
    }

    public User toEntity() {
        return User.builder()
                .username(username)
                .passwordHash(password)
                .email(email)
                .phoneNumber(phoneNumber)
                .gender(gender)
                .preferGender(preferGender)
                .build();
    }
}
