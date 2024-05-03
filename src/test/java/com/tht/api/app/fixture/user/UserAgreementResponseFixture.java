package com.tht.api.app.fixture.user;

import com.tht.api.app.entity.enums.AgreementCategory;
import com.tht.api.app.facade.user.response.UserAgreementResponse;

public class UserAgreementResponseFixture {

    private static boolean serviceUseAgree = true;

    private static boolean personalPrivacyInfoAgree = true;

    private static boolean locationServiceAgree = true;

    private static boolean marketingAgree = true;

    public static UserAgreementResponse make() {
        return new UserAgreementResponse(serviceUseAgree, personalPrivacyInfoAgree, locationServiceAgree, marketingAgree);
    }
}
