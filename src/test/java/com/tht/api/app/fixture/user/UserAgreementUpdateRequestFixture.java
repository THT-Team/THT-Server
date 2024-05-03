package com.tht.api.app.fixture.user;

import com.tht.api.app.entity.enums.AgreementCategory;
import com.tht.api.app.facade.user.request.UserAgreementUpdateRequest;

public class UserAgreementUpdateRequestFixture {

    private static AgreementCategory agreementName = AgreementCategory.LOCATION_SERVICE_AGREE;
    private static boolean value = true;

    public static UserAgreementUpdateRequest make() {
        return new UserAgreementUpdateRequest(agreementName, value);
    }
}
