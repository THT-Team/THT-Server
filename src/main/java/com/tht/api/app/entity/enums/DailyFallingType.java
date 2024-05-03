package com.tht.api.app.entity.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.tht.api.exception.custom.EnumStateNotFoundException;
import lombok.AllArgsConstructor;

import java.util.Arrays;

@AllArgsConstructor
public enum DailyFallingType implements EnumModel{

    ONE_CHOICE("oneChoice"),
    TWO_CHOICE("twoChoice"),
    FORE_CHOICE("foreChoice");

    private String value;

    @JsonCreator
    public static DailyFallingType toConverter(final String name) {
        return Arrays.stream(DailyFallingType.values())
                .filter(dailyFallingType -> dailyFallingType.getValue().equals(name))
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
