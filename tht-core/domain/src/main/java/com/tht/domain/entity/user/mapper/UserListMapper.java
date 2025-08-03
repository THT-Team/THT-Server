package com.tht.domain.entity.user.mapper;

import com.querydsl.core.annotations.QueryProjection;
import com.tht.enums.EntityState;
import java.time.Instant;

public record UserListMapper(
    String username,
    String profilePhotoUrl,
    String userUuid,
    Instant createdAt,
    EntityState userSate
) {

    @QueryProjection
    public UserListMapper {
    }
}
