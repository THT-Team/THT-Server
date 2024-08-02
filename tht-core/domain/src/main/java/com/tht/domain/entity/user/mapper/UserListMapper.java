package com.tht.domain.entity.user.mapper;

import com.querydsl.core.annotations.QueryProjection;
import com.tht.enums.EntityState;

import java.time.LocalDateTime;

public record UserListMapper(
    String username,
    String profilePhotoUrl,
    String userUuid,
    LocalDateTime createdAt,
    EntityState userSate
) {

    @QueryProjection
    public UserListMapper {
    }
}
