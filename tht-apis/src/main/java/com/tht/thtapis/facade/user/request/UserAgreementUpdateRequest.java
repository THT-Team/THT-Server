package com.tht.thtapis.facade.user.request;


import com.tht.infra.agreement.AgreementCategory;

public record UserAgreementUpdateRequest(

        AgreementCategory agreementName,

        boolean value
) {

}
