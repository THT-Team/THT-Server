package com.tht.thtadmin.fixture.user;

import com.tht.thtadmin.ui.user.response.BlockedUserInfoDto;

public class BlockedUserInfoDtoFixture {

    private static final String userUuid = "user-uuid";
    private static final String userName = "차단한 유저 닉네임";
    private static final String blockDateTime = "2024.05.16";

    public static BlockedUserInfoDto make() {
        return new BlockedUserInfoDto(userUuid, userName, blockDateTime);
    }
}
