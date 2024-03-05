package com.tht.api.app.entity.enums;

import com.tht.api.exception.custom.EnumStateNotFoundException;
import java.util.Arrays;

public enum SNSType {

    NORMAL,
    KAKAO,
    NAVER,
    GOOGLE;

//    @JsonCreator
    public static SNSType toConverter(final String name) {
        return Arrays.stream(SNSType.values())
            .filter(snsType -> snsType.name().equals(name))
            .findFirst()
            .orElseThrow(
                () -> EnumStateNotFoundException.ofSNSType(name)
            );
    }

    public static SNSType toSNSConverter(final String name) {

        if (SNSType.NORMAL.name().equals(name)) {
            throw EnumStateNotFoundException.ofSNSTypeNotNormal();
        }

        return Arrays.stream(SNSType.values())
            .filter(snsType -> snsType.name().equals(name))
            .findFirst()
            .orElseThrow(
                () -> EnumStateNotFoundException.ofSNSType(name)
            );
    }

    public boolean isSns() {
        return !this.equals(SNSType.NORMAL);
    }
}
