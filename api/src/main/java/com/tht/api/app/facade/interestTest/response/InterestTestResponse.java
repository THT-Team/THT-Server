package com.tht.api.app.facade.interestTest.response;

import com.tht.api.app.repository.mapper.InterestMapper;
import com.tht.api.app.repository.mapper.InterestTestMapper;

public record InterestTestResponse (Integer idx, String name) {

    public static InterestTestResponse of(final InterestTestMapper mapper) {
        return new InterestTestResponse(
                mapper.idx(),
                mapper.name()
        );
    }
}