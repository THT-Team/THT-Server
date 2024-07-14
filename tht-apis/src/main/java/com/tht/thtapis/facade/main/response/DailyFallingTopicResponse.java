package com.tht.thtapis.facade.main.response;


import com.tht.infra.dailyfalling.mapper.DailyFallingMapper;

public record DailyFallingTopicResponse(

    long idx,
    String keyword,
    int keywordIdx,
    String keywordImgUrl,
    String talkIssue
) {

    public static DailyFallingTopicResponse of(final DailyFallingMapper mapper) {
        return new DailyFallingTopicResponse(
            mapper.idx(),
            mapper.keyword(),
            mapper.keywordIdx(),
            mapper.keywordImgUrl(),
            mapper.talkIssue()
        );
    }
}
