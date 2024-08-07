package com.tht.enums.user;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.tht.enums.EnumModel;
import com.tht.enums.EnumStateNotFoundException;
import lombok.AllArgsConstructor;

import java.util.Arrays;

@AllArgsConstructor
public enum UserReligion implements EnumModel {

    NONE("NONE"),
    CHRISTIAN("CHRISTIAN"),
    CATHOLICISM("CATHOLICISM"),
    BUDDHISM("BUDDHISM"),
    WON_BUDDHISM("WON_BUDDHISM"),
    OTHER("OTHER");

    private final String value;

    @JsonCreator
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
