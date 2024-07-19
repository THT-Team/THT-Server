package com.tht.thtapis.facade.user.request;


import com.tht.enums.agreement.AgreementCategory;

public record UserAgreementUpdateRequest(

        AgreementCategory agreementName,

        boolean value
) {

}
