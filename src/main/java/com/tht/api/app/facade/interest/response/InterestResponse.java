package com.tht.api.app.facade.interest.response;

import com.tht.api.app.repository.mapper.InterestMapper;

public record InterestResponse (Integer idx, String name, String emojiCode){


    public static InterestResponse of(final InterestMapper mapper) {
        return new InterestResponse(
            mapper.idx(),
            mapper.name(),
            mapper.emojiCode()
        );
    }
}
