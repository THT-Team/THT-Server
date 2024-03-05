package com.tht.api.app.repository.mapper;

import com.querydsl.core.annotations.QueryProjection;
import java.time.LocalDateTime;

public record DailyFallingTimeMapper(
    Long dailyFallingIdx,
    LocalDateTime endDate
) {

    @QueryProjection
    public DailyFallingTimeMapper{}

}
