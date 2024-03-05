package com.tht.api.app.unit.fixture.user;

import com.tht.api.app.entity.enums.SNSType;
import com.tht.api.app.repository.mapper.UserSnsMapper;
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
