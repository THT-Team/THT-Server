package com.tht.enums.user;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.tht.enums.EnumModel;
import com.tht.enums.EnumStateNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@AllArgsConstructor
public enum UserFrequency implements EnumModel {

    NONE("NONE", "안 함"),
    SOMETIMES("SOMETIMES", "가끔"),
    FREQUENTLY("FREQUENTLY", "자주");

    private final String value;
    @Getter
    private final String desc;

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
