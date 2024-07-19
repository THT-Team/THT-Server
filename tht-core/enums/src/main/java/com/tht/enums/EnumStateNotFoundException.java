package com.tht.enums;

import com.tht.enums.agreement.AgreementCategory;
import com.tht.enums.dailyfalling.DailyFallingType;
import com.tht.enums.user.UserFrequency;
import com.tht.enums.user.UserReligion;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;

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

    public static EnumStateNotFoundException ofSNSTypeNotNormal() {

        return new EnumStateNotFoundException("SNS 통합회원가입은 NORMAL 을 제외한 유효한 타입만 가능합니다.");
    }

    public static EnumStateNotFoundException ofUserFrequency(final String findUserFrequency) {
        return new EnumStateNotFoundException(String.format("%s는 유효하지 않은 User Frequency 타입입니다. (ex) %s", findUserFrequency, getEnumValueList(UserFrequency.class)));
    }

    private static List<String> getEnumValueList(Class<? extends EnumModel> enums) {
        return Arrays.stream(enums.getEnumConstants()).map(EnumModel::getValue).toList();
    }

    public static EnumStateNotFoundException ofUserReligion(final String findReligionName) {
        return new EnumStateNotFoundException(String.format("%s는 유효하지 않은 User Religion 타입입니다. (ex) %s", findReligionName, getEnumValueList(UserReligion.class)));
    }

    public static EnumStateNotFoundException ofAgreement(final String agreementCategoryName) {
        return new EnumStateNotFoundException(String.format("%s는 유효하지 않은 약관동의 카테고리 타입입니다. (ex) %s", agreementCategoryName, getEnumValueList(AgreementCategory.class)));
    }

    public static EnumStateNotFoundException ofDailyFallingType(String type) {
        return new EnumStateNotFoundException(String.format("%s는 유효하지 않은 약관동의 카테고리 타입입니다. (ex) %s", type, getEnumValueList(DailyFallingType.class)));
    }
}