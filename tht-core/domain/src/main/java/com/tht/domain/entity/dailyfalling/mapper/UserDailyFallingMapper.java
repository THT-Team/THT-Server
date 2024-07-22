package com.tht.domain.entity.dailyfalling.mapper;

import com.querydsl.core.annotations.QueryProjection;

public record UserDailyFallingMapper(
    long idx,
    String userUuid,
    Long dailyFallingIdx
) {

    @QueryProjection
    public UserDailyFallingMapper {}

}
