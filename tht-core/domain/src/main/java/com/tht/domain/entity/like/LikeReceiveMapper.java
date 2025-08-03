package com.tht.domain.entity.like;

import com.querydsl.core.annotations.QueryProjection;
import java.time.Instant;
import java.time.LocalDate;

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
    Instant receivedTime
) {

    @QueryProjection
    public LikeReceiveMapper {
    }
}
