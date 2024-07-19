package com.tht.enums.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tht.enums.EnumModel;
import com.tht.enums.EnumStateNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum Gender implements EnumModel {

    MALE("MALE"),
    FEMALE("FEMALE"),
    BISEXUAL("BISEXUAL");

    private final String value;

    public static Gender toConverter(final String name) {
        return Arrays.stream(Gender.values())
            .filter(gender -> gender.name().equals(name))
            .findAny()
            .orElseThrow(() -> EnumStateNotFoundException.ofGender(name));
    }

    @Override
    public String getKey() {
        return name();
    }

    @Override
    public String getValue() {
        return value;
    }
}
