package com.tht.thtapis.fixture.setting;

import com.tht.thtapis.facade.setting.InquiryRequest;

public class InquiryRequestFixture {

    public static InquiryRequest make() {
        return new InquiryRequest(
            "email@email.com",
            "문의하기내용입니다 ~~ 아아아!!",
            true
        );
    }

}
