package com.tht.domain.entity.block.repository.mapper;

import com.querydsl.core.annotations.QueryProjection;
import com.tht.enums.EntityState;
import com.tht.enums.user.Gender;

import java.time.LocalDateTime;

public record UserBlockMapper(
    String userUuid,
    String username,
    Gender gender,
    EntityState userStatus,
    LocalDateTime currentBlockDate,
    String blockedUserName
) {

    @QueryProjection
    public UserBlockMapper {}
}
