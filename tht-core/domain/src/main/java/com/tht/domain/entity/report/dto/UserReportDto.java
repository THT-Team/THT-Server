package com.tht.domain.entity.report.dto;

import com.tht.domain.entity.report.repository.mapper.UserReportMapper;
import com.tht.enums.EntityState;
import com.tht.enums.user.Gender;
import com.tht.thtcommonutils.utils.CustomDateFormatUtils;

public record UserReportDto(
    String userUuid,
    String username,
    Gender gender,
    Gender preferGender,
    String reportDate,
    EntityState userStatus,
    String reportedUserName,
    String reason
) {
    public static UserReportDto ofMapper(final UserReportMapper mapper) {

        return new UserReportDto(
            mapper.userUuid(),
            mapper.username(),
            mapper.gender(),
            mapper.preferGender(),
            mapper.reportDate().format(CustomDateFormatUtils.getDateTimeInstance()),
            mapper.userStatus(),
            mapper.reportedUserName(),
            mapper.reason()
        );
    }
}
