package com.tht.api.app.facade.user.request;

import com.tht.api.app.entity.user.UserAgreement;

public record UserAgreementRequest(
    boolean serviceUseAgree,
    boolean personalPrivacyInfoAgree,
    boolean locationServiceAgree,
    boolean marketingAgree
) {

    public void valid() {
        validPersonalPrivacyInfoAgree();
        validServiceUseAgree();
        validLocationServiceAgree();
    }

    private void validServiceUseAgree() {
        if (!serviceUseAgree) {
            throw new IllegalArgumentException("이용 약관 동의는 필수입니다.");
        }
    }

    private void validPersonalPrivacyInfoAgree() {
        if (!personalPrivacyInfoAgree) {
            throw new IllegalArgumentException("개인 정보 수집 및 이용 동의는 필수입니다.");
        }
    }

    private void validLocationServiceAgree() {
        if (!locationServiceAgree) {
            throw new IllegalArgumentException("위치 기반 서비스 약관 동의는 필수입니다.");
        }
    }

    public UserAgreement toEntity() {
        return UserAgreement.create(
            serviceUseAgree,
            personalPrivacyInfoAgree,
            locationServiceAgree,
            marketingAgree
        );
    }

}
