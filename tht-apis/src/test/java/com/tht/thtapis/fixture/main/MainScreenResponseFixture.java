package com.tht.thtapis.fixture.main;

import com.tht.thtapis.facade.user.response.MainScreenResponse;
import com.tht.thtapis.facade.user.response.MainScreenUserInfoResponse;

import java.util.List;

public class MainScreenResponseFixture {

    private static final long selectDailyFallingIdx = 123;
    private static final long topicExpirationUnixTime = 1451021213;
    private static final boolean isLast = false;
    private static final List<MainScreenUserInfoResponse> userInfos = List.of(
        MainScreenUserInfoResponseFixture.make());

    public static MainScreenResponse make() {
        return new MainScreenResponse(
            selectDailyFallingIdx,
            topicExpirationUnixTime,
            isLast,
            userInfos
        );
    }
}
