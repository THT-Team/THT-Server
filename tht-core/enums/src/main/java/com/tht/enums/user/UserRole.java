package com.tht.enums.user;



import com.tht.enums.EnumStateNotFoundException;

import java.util.Arrays;

public enum UserRole {
    ADMIN,
    NORMAL;

    public static UserRole toConverter(final String name) {
        return Arrays.stream(UserRole.values())
            .filter(userRole -> userRole.name().equals(name))
            .findFirst()
            .orElseThrow(
                () -> EnumStateNotFoundException.ofUserRole(name)
            );
    }
}
