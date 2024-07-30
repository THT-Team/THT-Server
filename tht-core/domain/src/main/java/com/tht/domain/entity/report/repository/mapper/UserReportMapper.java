package com.tht.domain.entity.report.repository.mapper;

import com.querydsl.core.annotations.QueryProjection;
import com.tht.enums.EntityState;
import com.tht.enums.user.Gender;

import java.time.LocalDateTime;

public record UserReportMapper(
    String userUuid,
    String username,
    Gender gender,
    Gender preferGender,
    LocalDateTime reportDate,
    EntityState userStatus,
    String reportedUserName,
    String reason
) {
    @QueryProjection
    public UserReportMapper {
    }
}
