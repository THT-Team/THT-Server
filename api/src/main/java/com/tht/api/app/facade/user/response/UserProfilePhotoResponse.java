package com.tht.api.app.facade.user.response;

import com.tht.api.app.repository.mapper.UserProfilePhotoMapper;

public record UserProfilePhotoResponse(
    String url,
    int priority
) {

    public static UserProfilePhotoResponse of(final UserProfilePhotoMapper mapper) {
        return new UserProfilePhotoResponse(mapper.url(), mapper.priority());
    }
}
