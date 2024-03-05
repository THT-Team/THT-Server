package com.tht.api.app.unit.fixture.user;

import com.tht.api.app.facade.user.request.UserLocationRequest;

public class UserLocationRequestFixture {

    private final static String address = "서울특별시 힘찬구 열심동";
    private final static Integer regionCode = 110444103;
    private final static float lat = 37.5F;
    private final static float lon = 127.7F;

    private UserLocationRequestFixture(){}

    public static UserLocationRequest make() {
        return new UserLocationRequest(address, regionCode, lat, lon);
    }
}
