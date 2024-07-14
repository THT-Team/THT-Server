package com.tht.thtapis.fixture.user;

import com.tht.infra.user.enums.SNSType;
import com.tht.thtapis.facade.user.request.UserSnsSignUpRequest;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserSnsSignUpRequestFixture {

    private static final String phoneNumber = "01012341234";
    private static final String deviceKey = "device-key";
    private static final SNSType naverType = SNSType.NAVER;
    private static final String snsUniqueId = "sns unique id";
    private static final String email = "email@email.com";


    public static UserSnsSignUpRequest makeSNSType() {
        return new UserSnsSignUpRequest(phoneNumber, deviceKey, naverType, snsUniqueId, email);
    }
}
