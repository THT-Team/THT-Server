package com.tht.api.app.repository.mapper;

import com.querydsl.core.annotations.QueryProjection;

public record DailyFallingMapper(
    long idx,
    String keyword,
    String keywordImgUrl,
    String talkIssue
) {

    @QueryProjection
    public DailyFallingMapper{}

}
