package com.tht.domain.entity.user.service.dto;

import com.tht.domain.entity.user.mapper.UserListMapper;
import com.tht.enums.EntityState;
import com.tht.thtcommonutils.utils.CustomDateFormatUtils;

public record UserListDto(
    String username,
    String profilePhotoUrl,
    String userUuid,
    String createdAt,
    EntityState userSate
) {
    public static UserListDto ofMapper(final UserListMapper mapper) {
        return new UserListDto(
            mapper.username(),
            mapper.profilePhotoUrl(),
            mapper.userUuid(),
            mapper.createdAt().format(CustomDateFormatUtils.getDateTimeInstance()),
            mapper.userSate()
        );
    }
}
