package com.tht.thtadmin.ui.user.response;

import com.tht.enums.user.Gender;

import java.util.List;

public record UserReportResponse(
    String userUuid,
    String username,
    Gender gender,
    Gender preferGender,
    int reportCount,
    String currentReportDate,
    List<UserReportDetailInfoDto> detailInfoList
) {
}

record UserReportDetailInfoDto(
    String blockedUserName,
    String blockedUserUuid,
    String blockTime,
    String reason
) { }
