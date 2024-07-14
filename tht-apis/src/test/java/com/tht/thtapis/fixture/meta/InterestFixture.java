package com.tht.thtapis.fixture.meta;

import com.tht.infra.interesst.InterestMapper;
import com.tht.thtapis.facade.interest.response.InterestResponse;

public class InterestFixture {

    private static final Integer idx = 1;
    private static final String name = "등산";
    private static final String emojiCode = "#123df3";

    public static InterestResponse responseMake() {
        return new InterestResponse(idx, name, emojiCode);
    }

    public static InterestMapper mapperMake() {
        return new InterestMapper(idx, name, emojiCode);
    }
}
