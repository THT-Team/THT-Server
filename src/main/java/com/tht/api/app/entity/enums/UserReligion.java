package com.tht.api.app.entity.enums;

import com.tht.api.exception.custom.EnumStateNotFoundException;
import lombok.AllArgsConstructor;

import java.util.Arrays;

@AllArgsConstructor
public enum UserReligion implements EnumModel{

    NONE("NONE"),
    CHRISTIAN("CHRISTIAN"),
    CATHOLICISM("CATHOLICISM"),
    BUDDHISM("BUDDHISM"),
    WON_BUDDHISM("WON_BUDDHISM"),
    OTHER("OTHER");

    private final String value;

    public static UserReligion toConverter(final String name) {
        return Arrays.stream(UserReligion.values())
                .filter(userReligion -> userReligion.getValue().equals(name))
                .findFirst()
                .orElseThrow(
                        () -> EnumStateNotFoundException.ofUserReligion(name)
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
