package com.tht.api.app.unit.fixture.main;

import com.tht.api.app.repository.mapper.UserDailyFallingMapper;

public class UserDailyFallingMapperFixture {

    private final static long idx = 1;
    private final static String userUuid = "user_uuid";
    private final static Long dailyFallingIdx = 1L;

    public static UserDailyFallingMapper make() {
        return new UserDailyFallingMapper(
            idx,
            userUuid,
            dailyFallingIdx
        );
    }
}
