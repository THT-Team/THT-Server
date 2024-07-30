package com.tht.thtadmin.ui.user.response;

import com.tht.domain.entity.block.dto.UserBlockDto;
import com.tht.enums.EntityState;
import com.tht.enums.user.Gender;

public record UserBlockResponse(
    String userUuid,
    String username,
    Gender gender,
    EntityState userStatus,
    String currentBlockDate,
    String blockedUserName
) {
    public static UserBlockResponse ofDto(final UserBlockDto dto) {
        return new UserBlockResponse(
            dto.userUuid(),
            dto.username(),
            dto.gender(),
            dto.userStatus(),
            dto.currentBlockDate(),
            dto.blockedUserName()
        );
    }
}

