package com.tht.infra.like;

import com.querydsl.core.annotations.QueryProjection;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record LikeReceiveMapper(

    long dailyFallingIdx,
    long likeIdx,
    String topic,
    String issue,
    String userUuid,
    String username,
    String profileUrl,
    LocalDate birthDay,
    String address,
    LocalDateTime receivedTime
) {

    @QueryProjection
    public LikeReceiveMapper {}
}
