package com.tht.api.app.entity.enums;

import com.tht.api.exception.custom.EnumStateNotFoundException;
import lombok.AllArgsConstructor;

import java.util.Arrays;

@AllArgsConstructor
public enum AgreementCategory implements EnumModel{

    SERVICE_USER_AGREE("serviceUserAgree"),
    PERSONAL_PRIVACY_INFO_AGREE("personalPrivacyInfoAgree"),
    LOCATION_SERVICE_AGREE("locationServiceAgree"),
    MARKETING_AGREE("marketingAgree");

    private final String value;

    public static AgreementCategory toConverter(final String name) {
        return Arrays.stream(AgreementCategory.values())
                .filter(agreement -> agreement.getValue().equals(name))
                .findAny()
                .orElseThrow(() -> EnumStateNotFoundException.ofAgreement(name));
    }

    @Override
    public String getKey() {
        return name();
    }

    @Override
    public String getValue() {
        return value;
    }
}
