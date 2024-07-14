package com.tht.infra.dailyfalling;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.tht.infra.EnumModel;
import com.tht.infra.exception.EnumStateNotFoundException;
import lombok.AllArgsConstructor;

import java.util.Arrays;

@AllArgsConstructor
public enum DailyFallingType implements EnumModel {

    ONE_CHOICE("oneChoice"),
    TWO_CHOICE("twoChoice"),
    FOUR_CHOICE("fourChoice");

    private final String value;

    @JsonCreator
    public static DailyFallingType toConverter(final String name) {
        return Arrays.stream(DailyFallingType.values())
                .filter(dailyFallingType -> dailyFallingType.name().equals(name))
                .findAny()
                .orElseThrow(() -> EnumStateNotFoundException.ofDailyFallingType(name));
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
