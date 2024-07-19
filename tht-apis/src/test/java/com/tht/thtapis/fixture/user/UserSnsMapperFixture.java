package com.tht.thtapis.fixture.user;

import com.tht.enums.user.SNSType;
import com.tht.infra.user.mapper.UserSnsMapper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserSnsMapperFixture {

    private static final String userUuid = "user-uuid";
    private static final String phoneNumber = "1012312324";

    public static UserSnsMapper ofType(final SNSType snsType) {
        return new UserSnsMapper(phoneNumber, userUuid, snsType);
    }
}
