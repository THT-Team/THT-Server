package com.tht.thtapis.fixture.main;

import com.tht.thtapis.facade.user.response.MainScreenResponse;
import com.tht.thtapis.facade.user.response.MainScreenUserInfoResponse;

import java.util.List;

public class MainScreenResponseFixture {

    private static final long SELECT_DAILY_FALLING_IDX = 123;
    private static final long TOPIC_EXPIRATION_UNIX_TIME = 1451021213;
    private static final boolean IS_LAST = false;
    private static final boolean IS_USERS_LAST = false;
    private static final List<MainScreenUserInfoResponse> USER_INFO_RESPONSES = List.of(
        MainScreenUserInfoResponseFixture.make());

    public static MainScreenResponse make() {
        return new MainScreenResponse(
            SELECT_DAILY_FALLING_IDX,
            TOPIC_EXPIRATION_UNIX_TIME,
            IS_LAST,
            IS_USERS_LAST,
            USER_INFO_RESPONSES
        );
    }
}
