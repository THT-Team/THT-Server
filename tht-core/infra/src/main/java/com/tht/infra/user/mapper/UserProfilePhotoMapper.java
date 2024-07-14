package com.tht.infra.user.mapper;

import com.querydsl.core.annotations.QueryProjection;

public record UserProfilePhotoMapper(
    Long idx,
    String userUuid,
    String url,
    Integer priority
) {

    @QueryProjection
    public UserProfilePhotoMapper {}
}
