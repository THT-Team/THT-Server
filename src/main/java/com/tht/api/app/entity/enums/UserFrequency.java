package com.tht.api.app.entity.enums;

import com.tht.api.exception.custom.EnumStateNotFoundException;

import java.util.Arrays;

public enum UserFrequency {

    NONE,
    SOMETIMES,
    FREQUENTLY;

    public static UserFrequency toConverter(final String name) {
        return Arrays.stream(UserFrequency.values())
                .filter(userFrequency -> userFrequency.name().equals(name))
                .findFirst()
                .orElseThrow(
                        () -> EnumStateNotFoundException.ofUserFrequency(name)
                );
    }
}
