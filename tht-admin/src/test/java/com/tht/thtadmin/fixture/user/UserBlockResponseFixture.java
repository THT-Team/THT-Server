package com.tht.thtadmin.fixture.user;

import com.tht.enums.EntityState;
import com.tht.enums.user.Gender;
import com.tht.thtadmin.ui.user.response.UserBlockResponse;

public class UserBlockResponseFixture {

    private static final String userUuid = "user-uuid";
    private static final String username = "회원 닉네임";
    private static final Gender gender = Gender.MALE;
    private static final EntityState userStatus = EntityState.ACTIVE;
    private static final String currentBlockDate = "2024-07-30 15:29:16";
    private static final String blockedUserName = "신고한 유저 이름";

    public static UserBlockResponse make() {
        return new UserBlockResponse(userUuid, username, gender, userStatus, currentBlockDate, blockedUserName);
    }
}
