package com.tht.thtapis.facade.interest.response;


import com.tht.infra.interesst.Interest;
import com.tht.infra.interesst.InterestMapper;

public record InterestResponse (Integer idx, String name, String emojiCode){


    public static InterestResponse of(final InterestMapper mapper) {
        return new InterestResponse(
            mapper.idx(),
            mapper.name(),
            mapper.emojiCode()
        );
    }

    public static InterestResponse of(final Interest mapper) {
        return new InterestResponse(
            mapper.getIdx(),
            mapper.getName(),
            mapper.getEmojiCode()
        );
    }
}
