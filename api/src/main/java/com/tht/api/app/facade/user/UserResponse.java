package com.tht.api.app.facade.user;

import com.tht.api.app.entity.user.Gender;
import com.tht.api.app.entity.user.User;

public record UserResponse (
        Long userId,
        String username,
        Gender gender,
        Gender preferGender
) {
    public static UserResponse fromEntity(final User user) {
        return new UserResponse(
                user.getIdx(),
                user.getUsername(),
                user.getGender(),
                user.getPreferGender()
        );
    }
}
