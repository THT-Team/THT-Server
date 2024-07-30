package com.tht.thtadmin.ui.user.response;

import com.tht.domain.entity.user.service.dto.WithDrawUserDto;
import com.tht.enums.EntityState;

public record WithDrawUserResponse(
    String userUuid,
    String username,
    String reason,
    String feedBack,
    EntityState userStatus,
    String requestDate
) {
    public static WithDrawUserResponse ofDto(final WithDrawUserDto dto) {
        return new WithDrawUserResponse(
            dto.userUuid(),
            dto.username(),
            dto.reason(),
            dto.feedBack(),
            dto.userStatus(),
            dto.requestDate()
        );
    }
}
