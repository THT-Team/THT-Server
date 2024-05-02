package com.tht.api.app.facade.user.request;

import com.tht.api.app.entity.user.UserAgreement;
import jakarta.validation.constraints.AssertTrue;

public record UserAgreementRequest(
    @AssertTrue(message = "이용 약관 동의는 필수입니다.")
    boolean serviceUseAgree,
    @AssertTrue(message = "개인 정보 수집 및 이용 동의는 필수입니다.")
    boolean personalPrivacyInfoAgree,
    boolean marketingAgree
){

    public UserAgreement toEntity(final String userUuid) {
        return UserAgreement.create(
            userUuid,
            serviceUseAgree,
            personalPrivacyInfoAgree,
            marketingAgree
        );
    }

}
