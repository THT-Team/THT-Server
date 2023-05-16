package com.tht.api.exception.custom;

import com.tht.api.app.entity.enums.SNSType;
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

}
