package com.tht.domain.entity.like;

import com.querydsl.core.annotations.QueryProjection;

import java.time.Instant;

public record LikeReceiveMapper(

    long dailyFallingIdx,
    long likeIdx,
    String topic,
    String issue,
    String userUuid,
    String username,
    String profileUrl,
    Instant birthDay,
    String address,
    Instant receivedTime
) {

    @QueryProjection
    public LikeReceiveMapper {}
}
