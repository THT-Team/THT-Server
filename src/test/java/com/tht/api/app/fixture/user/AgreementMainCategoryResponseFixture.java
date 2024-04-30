package com.tht.api.app.fixture.user;

import com.tht.api.app.facade.user.response.AgreementMainCategoryResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AgreementMainCategoryResponseFixture {

    static String name = "serviceUseAgree";
    static String subject = "이용약관을 읽고, 이해했으며, 동의합니다.";
    static boolean isRequired = true;
    static String description = "";

    public static AgreementMainCategoryResponse make() {
        return AgreementMainCategoryResponse.of(name, subject, isRequired, description);
    }
}
