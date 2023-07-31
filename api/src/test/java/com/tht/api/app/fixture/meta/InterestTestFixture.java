package com.tht.api.app.fixture.meta;

import com.tht.api.app.entity.meta.InterestTest;
import com.tht.api.app.facade.interestTest.response.InterestTestResponse;
import com.tht.api.app.repository.mapper.InterestTestMapper;

public class InterestTestFixture {
    private static final Integer idx = 1;
    private static final String name = "등산";

    public static InterestTestResponse responseMake(){
        return new InterestTestResponse(idx, name);
    }

    public static InterestTestMapper mapperMake(){
        return new InterestTestMapper(idx, name);
    }
}
