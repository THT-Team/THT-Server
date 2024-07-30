package com.tht.thtadmin.fixture.user;

import com.tht.enums.EntityState;
import com.tht.thtadmin.ui.user.response.WithDrawUserResponse;

public class WithDrawUserResponseFixture {
    private static final String userUuid = "user-uuid";
    private static final String username = "탈퇴요청회원 이름";
    private static final String reason = "탈퇴 이유";
    private static final String feedBack = " 피드백 ";
    private static final EntityState userStatus = EntityState.WITHDRAW_REQUEST;
    private static final String requestDate = "2020.01.01 00:00:00";

    public static WithDrawUserResponse make() {
        return new WithDrawUserResponse(userUuid, username, reason, feedBack, userStatus, requestDate);
    }
}
