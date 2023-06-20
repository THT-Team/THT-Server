package com.tht.api.app.facade.main.response;

import com.tht.api.app.repository.mapper.DailyFallingMapper;

public record DailyFallingResponse(

    long idx,
    String keyword,
    String keywordImgUrl,
    String talkIssue
) {

    public static DailyFallingResponse of(final DailyFallingMapper mapper) {
        return new DailyFallingResponse(
            mapper.idx(),
            mapper.keyword(),
            mapper.keywordImgUrl(),
            mapper.talkIssue()
        );
    }
}
