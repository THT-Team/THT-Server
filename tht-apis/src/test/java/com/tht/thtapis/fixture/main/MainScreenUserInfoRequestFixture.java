package com.tht.thtapis.fixture.main;


import com.tht.thtapis.facade.user.request.MainScreenUserInfoRequest;

public class MainScreenUserInfoRequestFixture {

    private static final Long userDailyFallingCourserIdx = 1L;
    private static final Integer size = 100;

    public static MainScreenUserInfoRequest make() {

        return new MainScreenUserInfoRequest(
            userDailyFallingCourserIdx,
            size
        );
    }

}
