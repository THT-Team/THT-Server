package com.tht.api.exception.custom;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EnumStateNotFoundException extends RuntimeException {

    public EnumStateNotFoundException(final String message) {
        super(message);
    }

    public static EnumStateNotFoundException ofGender(final String findGender) {

        return new EnumStateNotFoundException(String.format("성별에 %s가 존재하지 않습니다.", findGender));
    }
}