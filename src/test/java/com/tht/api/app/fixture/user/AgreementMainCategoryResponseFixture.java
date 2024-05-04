package com.tht.api.app.fixture.user;

import com.tht.api.app.facade.user.response.AgreementMainCategoryResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AgreementMainCategoryResponseFixture {

    static String name = "serviceUseAgree";
    static String subject = "이용약관을 읽고, 이해했으며, 동의합니다.";
    static boolean isRequired = true;
    static String description = "폴링에서 제공하는 이벤트/혜택 등 다양한 정보를 Push 알림으로 받아보실 수 있습니다.";
    static String detailLink = "https://www.notion.so/janechoi/526c51e9cb584f29a7c16251914bb3cb?pvs=4";

    public static AgreementMainCategoryResponse make() {
        return AgreementMainCategoryResponse.of(name, subject, isRequired, description, detailLink);
    }
}
