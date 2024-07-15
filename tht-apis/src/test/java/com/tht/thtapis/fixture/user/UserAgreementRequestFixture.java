package com.tht.thtapis.fixture.user;


import com.tht.thtapis.facade.user.request.UserAgreementRequest;

public class UserAgreementRequestFixture {

    private final static boolean serviceUseAgree = true;
    private final static boolean personalPrivacyInfoAgree = true;
    private final static boolean marketingAgree = true;
    private final static boolean locationServiceAgree = true;

    private UserAgreementRequestFixture() {
    }

    public static UserAgreementRequest make() {
        return new UserAgreementRequest(
            serviceUseAgree, personalPrivacyInfoAgree, locationServiceAgree, marketingAgree);
    }
}
