package com.tht.api.app.fixture.user;

import com.tht.api.app.facade.user.request.UserAgreementUpdateRequest;

public class UserAgreementUpdateRequestFixture {

    private static String agreementName = "locationServiceAgree";
    private static boolean value = true;

    public static UserAgreementUpdateRequest make() {
        return new UserAgreementUpdateRequest(agreementName, value);
    }
}
