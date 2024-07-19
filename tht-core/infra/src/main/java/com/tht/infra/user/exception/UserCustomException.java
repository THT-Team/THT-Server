package com.tht.infra.user.exception;

import com.tht.enums.user.SNSType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserCustomException extends RuntimeException {

    private HttpStatus status;

    public UserCustomException(final String message) {
        super(message);
        this.status = BAD_REQUEST;
    }

    public UserCustomException(final String message, final HttpStatus status) {
        super(message);
        this.status = status;
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
        return new UserCustomException(String.format("uuid : %s 유저의 위치정보가 존재하지 않습니다.", uuid));
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

    public static UserCustomException noneValidUuidToAuth(final String userUuid) {
        return new UserCustomException(userUuid + " 는 존재하지 않는 회원번호 입니다.");
    }

    public static UserCustomException noneValidPhoneNumberToAuth(final String phoneNumber) {
        return new UserCustomException(phoneNumber + " 는 유효하지 않은 회원 전화번호 입니다.");
    }

}
