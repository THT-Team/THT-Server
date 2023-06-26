package com.tht.api.app.facade.main.response;

import com.tht.api.app.repository.mapper.DailyFallingMapper;

public record DailyFallingTopicResponse(

    long idx,
    String keyword,
    String keywordImgUrl,
    String talkIssue
) {

    public static DailyFallingTopicResponse of(final DailyFallingMapper mapper) {
        return new DailyFallingTopicResponse(
            mapper.idx(),
            mapper.keyword(),
            mapper.keywordImgUrl(),
            mapper.talkIssue()
        );
    }
}
