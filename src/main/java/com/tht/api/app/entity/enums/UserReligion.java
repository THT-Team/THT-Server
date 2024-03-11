package com.tht.api.app.entity.enums;

import com.tht.api.exception.custom.EnumStateNotFoundException;

import java.util.Arrays;

public enum UserReligion {

    NONE,
    CHRISTIAN,
    CATHOLICISM,
    BUDDHISM,
    WON_BUDDHISM,
    OTHER;

    public static UserReligion toConverter(final String name) {
        return Arrays.stream(UserReligion.values())
                .filter(userReligion -> userReligion.name().equals(name))
                .findFirst()
                .orElseThrow(
                        () -> EnumStateNotFoundException.ofUserReligion(name)
                );
    }
}
