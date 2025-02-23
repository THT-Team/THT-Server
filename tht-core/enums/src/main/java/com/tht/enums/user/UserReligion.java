package com.tht.enums.user;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.tht.enums.EnumModel;
import com.tht.enums.EnumStateNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@AllArgsConstructor
public enum UserReligion implements EnumModel {

    NONE("NONE", "무교"),
    CHRISTIAN("CHRISTIAN", "기독교"),
    CATHOLICISM("CATHOLICISM", "천주교"),
    BUDDHISM("BUDDHISM", "불교"),
    WON_BUDDHISM("WON_BUDDHISM", "원불"),
    OTHER("OTHER", "기타");

    private final String value;
    @Getter
    private final String desc;

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
