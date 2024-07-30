package com.tht.domain.entity.user.repository.querydsl.mapper;

import com.querydsl.core.annotations.QueryProjection;
import com.tht.enums.EntityState;

import java.time.LocalDateTime;

public record UserWithDrawLogMapper(
    String userUuid,
    String username,
    String reason,
    String feedBack,
    EntityState userStatus,
    LocalDateTime requestDate
) {

    @QueryProjection
    public UserWithDrawLogMapper {
    }
}
