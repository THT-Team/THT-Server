package com.tht.thtapis.facade.user.response;


import com.tht.infra.user.UserAgreement;

public record UserAgreementResponse(
        boolean serviceUseAgree,

        boolean personalPrivacyInfoAgree,

        boolean locationServiceAgree,

        boolean marketingAgree
) {

    public static UserAgreementResponse of(final UserAgreement userAgreement) {
        return new UserAgreementResponse(
                userAgreement.isServiceUseAgree(),
                userAgreement.isPersonalPrivacyInfoAgree(),
                userAgreement.isLocationServiceAgree(),
                userAgreement.isMarketingAgree()
        );
    }
}
