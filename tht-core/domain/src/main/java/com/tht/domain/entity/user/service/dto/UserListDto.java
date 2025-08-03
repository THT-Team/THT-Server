package com.tht.domain.entity.user.service.dto;

import com.tht.domain.entity.user.mapper.UserListMapper;
import com.tht.enums.EntityState;
import java.time.Instant;

public record UserListDto(
    String username,
    String profilePhotoUrl,
    String userUuid,
    Instant createdAt,
    EntityState userSate
) {
    public static UserListDto ofMapper(final UserListMapper mapper) {
        return new UserListDto(
            mapper.username(),
            mapper.profilePhotoUrl(),
            mapper.userUuid(),
            mapper.createdAt(),
            mapper.userSate()
        );
    }
}
