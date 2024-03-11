package com.tht.api.app.entity.enums;

import com.tht.api.exception.custom.EnumStateNotFoundException;
import lombok.AllArgsConstructor;

import java.util.Arrays;

@AllArgsConstructor
public enum UserFrequency implements EnumModel{

    NONE("NONE"),
    SOMETIMES("SOMETIMES"),
    FREQUENTLY("FREQUENTLY");

    private final String value;

    public static UserFrequency toConverter(final String name) {
        return Arrays.stream(UserFrequency.values())
                .filter(userFrequency -> userFrequency.getValue().equals(name))
                .findFirst()
                .orElseThrow(
                        () -> EnumStateNotFoundException.ofUserFrequency(name)
                );
    }

    @Override
    public String getKey() {
        return this.name();
    }

    @Override
    public String getValue() {
        return value;
    }
}
