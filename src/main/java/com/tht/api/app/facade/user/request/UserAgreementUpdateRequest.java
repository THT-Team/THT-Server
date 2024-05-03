package com.tht.api.app.facade.user.request;

import com.tht.api.app.entity.enums.AgreementCategory;

public record UserAgreementUpdateRequest(

        AgreementCategory agreementName,

        boolean value
) {

}
