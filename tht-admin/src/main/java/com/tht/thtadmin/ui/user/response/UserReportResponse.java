package com.tht.thtadmin.ui.user.response;

import com.tht.enums.EntityState;
import com.tht.enums.user.Gender;

public record UserReportResponse(
    String userUuid,
    String username,
    Gender gender,
    Gender preferGender,
    int reportCount,
    String reportDate,
    EntityState userStatus,
    String reportedUserName,
    String reason
) {
}