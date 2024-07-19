package com.tht.enums.user;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.tht.enums.EnumModel;
import com.tht.enums.EnumStateNotFoundException;
import lombok.AllArgsConstructor;

import java.util.Arrays;

@AllArgsConstructor
public enum UserFrequency implements EnumModel {

    NONE("NONE"),
    SOMETIMES("SOMETIMES"),
    FREQUENTLY("FREQUENTLY");

    private final String value;

    @JsonCreator
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
