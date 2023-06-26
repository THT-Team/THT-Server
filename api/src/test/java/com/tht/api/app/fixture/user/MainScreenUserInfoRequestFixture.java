package com.tht.api.app.fixture.user;

import com.tht.api.app.facade.user.request.MainScreenUserInfoRequest;
import java.util.List;

public class MainScreenUserInfoRequestFixture {

    private static final List<String> alreadySeenUserIdxList = List.of("uuid1", "uuid2", "uuid3");
    private static final Long userDailyFallingCourserIdx = 131L;
    private static final Integer size = 100;

    public static MainScreenUserInfoRequest make() {

        return new MainScreenUserInfoRequest(
            alreadySeenUserIdxList,
            userDailyFallingCourserIdx,
            size
        );
    }

}
