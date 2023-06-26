package com.tht.api.app.repository.mapper;

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
