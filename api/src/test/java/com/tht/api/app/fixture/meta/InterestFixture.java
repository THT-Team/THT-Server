package com.tht.api.app.fixture.meta;

import com.tht.api.app.facade.interest.response.InterestResponse;
import com.tht.api.app.repository.mapper.InterestMapper;

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
