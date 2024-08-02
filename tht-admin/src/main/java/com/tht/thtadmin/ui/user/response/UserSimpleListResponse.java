package com.tht.thtadmin.ui.user.response;

import com.tht.enums.EntityState;

public record UserSimpleListResponse(
    String username,
    String profilePhotoUrl,
    String userUuid,
    String createdAt,
    EntityState userSate
) {

    public static UserSimpleListResponse of(final String username, final String profile, final String uuid, final String createdAt, final EntityState userSate) {

        return new UserSimpleListResponse(username, profile, uuid, createdAt, userSate);
    }
}
