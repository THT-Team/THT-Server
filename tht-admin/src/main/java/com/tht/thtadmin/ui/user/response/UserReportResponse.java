package com.tht.thtadmin.ui.user.response;

import com.tht.domain.entity.report.dto.UserReportDto;
import com.tht.enums.EntityState;
import com.tht.enums.user.Gender;

public record UserReportResponse(
    String userUuid,
    String username,
    Gender gender,
    Gender preferGender,
    String reportDate,
    EntityState userStatus,
    String reportedUserName,
    String reason
) {
    public static UserReportResponse ofDto(final UserReportDto dto) {
        return new UserReportResponse(
            dto.userUuid(),
            dto.username(),
            dto.gender(),
            dto.preferGender(),
            dto.reportDate(),
            dto.userStatus(),
            dto.reportedUserName(),
            dto.reason()
        );
    }
}