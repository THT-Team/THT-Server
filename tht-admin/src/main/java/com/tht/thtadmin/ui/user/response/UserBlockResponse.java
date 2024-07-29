package com.tht.thtadmin.ui.user.response;

import com.tht.enums.EntityState;
import com.tht.enums.user.Gender;

import java.util.List;

public record UserBlockResponse(
    String userUuid,
    String username,
    Gender gender,
    EntityState userStatus,
    int blockCount,
    String currentBlockDate,
    List<BlockedUserInfoDto> blockedUserList
) {
}

