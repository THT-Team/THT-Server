package com.tht.thtadmin.fixture.user;

import com.tht.enums.EntityState;
import com.tht.enums.user.Gender;
import com.tht.thtadmin.ui.user.response.UserReportResponse;

public class UserReportResponseFixture {

    private static final String userUuid = "user-uuid";
    private static final String username = "신고당한 회원 이름";
    private static final Gender gender = Gender.MALE;
    private static final Gender preferGender = Gender.FEMALE;
    private static final int reportCount = 10;
    private static final String currentReportDate ="2024-07-30 15:29:16";
    private static final EntityState userStatus = EntityState.ACTIVE;
    private static final String reportedUser = "신고한 유저 이름";
    private static final String reason = "신고한 이유";

    public static UserReportResponse make() {
        return new UserReportResponse(userUuid, username, gender, preferGender, reportCount,
            currentReportDate, userStatus, reportedUser, reason);
    }
}
