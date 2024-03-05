package com.tht.api.app.repository.mapper;

import com.querydsl.core.annotations.QueryProjection;

public record UserDailyFallingMapper(
    long idx,
    String userUuid,
    Long dailyFallingIdx
) {

    @QueryProjection
    public UserDailyFallingMapper {}

}
