package com.tht.enums.agreement;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.tht.enums.EnumModel;
import com.tht.enums.EnumStateNotFoundException;
import lombok.AllArgsConstructor;

import java.util.Arrays;

@AllArgsConstructor
public enum AgreementCategory implements EnumModel {

    SERVICE_USE_AGREE("serviceUseAgree"),
    PERSONAL_PRIVACY_INFO_AGREE("personalPrivacyInfoAgree"),
    LOCATION_SERVICE_AGREE("locationServiceAgree"),
    MARKETING_AGREE("marketingAgree");

    private final String value;

    @JsonCreator
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
