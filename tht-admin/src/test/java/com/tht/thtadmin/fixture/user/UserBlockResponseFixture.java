package com.tht.thtadmin.fixture.user;

import com.tht.enums.EntityState;
import com.tht.enums.user.Gender;
import com.tht.thtadmin.ui.user.response.BlockedUserInfoDto;
import com.tht.thtadmin.ui.user.response.UserBlockResponse;

import java.util.List;

public class UserBlockResponseFixture {

    private static final String userUuid = "user-uuid";
    private static final String username = "회원 닉네임";
    private static final Gender gender = Gender.MALE;
    private static final EntityState userStatus = EntityState.ACTIVE;
    private static final int blockCount = 10;
    private static final String currentBlockDate = "2024.05.16";

    private static final List<BlockedUserInfoDto> blockedUserList = List.of(BlockedUserInfoDtoFixture.make());

    public static UserBlockResponse make() {
        return new UserBlockResponse(userUuid, username, gender, userStatus, blockCount, currentBlockDate, blockedUserList);
    }
}
