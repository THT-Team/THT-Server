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

    public static EnumStateNotFoundException ofUserRole(final String findUserRole) {

        return new EnumStateNotFoundException(String.format("유저 권한 목록에 %s가 존재하지 않습니다.", findUserRole));
    }

    public static EnumStateNotFoundException ofSNSType(final String findSnsType) {

        return new EnumStateNotFoundException(String.format("%s 는 유효하지 않은 SNS 타입입니다.", findSnsType));
    }
}