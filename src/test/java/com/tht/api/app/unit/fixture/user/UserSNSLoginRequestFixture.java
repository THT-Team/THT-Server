package com.tht.api.app.unit.fixture.user;

import com.tht.api.app.entity.enums.SNSType;
import com.tht.api.app.facade.user.request.UserSNSLoginRequest;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserSNSLoginRequestFixture {

    private static final String email = "email@email.com";
    private static final SNSType naver = SNSType.NAVER;
    private static final String snsUniqueId = "sns unique id";
    private static final String deviceKey = "device-key";

    public static UserSNSLoginRequest make() {
        return new UserSNSLoginRequest(email, naver.name(), snsUniqueId, deviceKey);
    }

}
