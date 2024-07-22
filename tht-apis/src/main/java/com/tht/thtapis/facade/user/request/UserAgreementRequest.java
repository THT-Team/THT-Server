package com.tht.thtapis.facade.user.request;

import com.tht.domain.entity.user.UserAgreement;
import jakarta.validation.constraints.AssertTrue;

public record UserAgreementRequest(
        @AssertTrue(message = "이용 약관 동의는 필수입니다.")
        boolean serviceUseAgree,
        @AssertTrue(message = "개인 정보 수집 및 이용 동의는 필수입니다.")
        boolean personalPrivacyInfoAgree,
        boolean locationServiceAgree,
        boolean marketingAgree
) {

    public UserAgreement toEntity(final String userUuid) {
        return UserAgreement.create(
                userUuid,
                serviceUseAgree,
                personalPrivacyInfoAgree,
                locationServiceAgree,
                marketingAgree
        );
    }

}
