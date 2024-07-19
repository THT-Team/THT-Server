package com.tht.thtapis.fixture.user;


import com.tht.thtapis.facade.user.request.UserLoginRequest;

public class UserLoginRequestFixture {

    private static final String phoneNumber = "01012341234";
    private static final String deviceKey = "device-test-key";

    public static UserLoginRequest make() {
        return new UserLoginRequest(phoneNumber, deviceKey);
    }
}
