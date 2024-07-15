package com.tht.infra.user.exception;

import com.tht.infra.user.enums.SNSType;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserCustomException extends RuntimeException {

    public UserCustomException(final String message) {
        super(message);
    }

    public static UserCustomException duplicateIntegrated(final String phoneNumber,
        final SNSType snsType) {
        return new UserCustomException(
            phoneNumber + " : 해당번호로 가입된 " + snsType.name()
                + " 이 존재하거나, 해당 SNS 고유 아이디로 가입한 이력이 존재합니다.");
    }

    public static UserCustomException notExist() {
        return new UserCustomException("해당 SNS ID가 존재하지 않습니다.");
    }

    public static UserCustomException notExistLocationInfo(final String uuid) {
        return new UserCustomException("uuid : " + uuid + " 유저의 위치정보가 존재하지 않습니다.");
    }

    public static UserCustomException noneValidPhoneNumberFormat() {
        return new UserCustomException("핸드폰 번호는 숫자로 구성된 9~11자리여야 합니다.");
    }

    public static UserCustomException noneValidEmailFormat() {
        return new UserCustomException("이메일 양식이 맞지 않습니다.");
    }

    public static UserCustomException notExistAlarmInfo(final String uuid) {
        return new UserCustomException("uuid : " + uuid + " 유저의 알림 정보가 존재하지 않습니다.");
    }

    public static UserCustomException notExistUserAgreementsInfo(final String userUuid) {
        return new UserCustomException("uuid : " + userUuid + " 유저의 약관동의 정볻가 존재하지 않습니다.");
    }
}
