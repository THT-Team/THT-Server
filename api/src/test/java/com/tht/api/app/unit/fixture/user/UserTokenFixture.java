package com.tht.api.app.unit.fixture.user;

import com.tht.api.app.entity.user.UserToken;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserTokenFixture {

    private final static String userUuid = "user-uuid";
    private static final String accessToken = "access-token";

    public static UserToken make() {
        return UserToken.create(userUuid, accessToken);
    }

}
