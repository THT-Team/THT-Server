package com.tht.api.app.facade.user.request;

import com.tht.api.app.entity.user.UserProfilePhoto;

public record UserProfilePhotoRequest(
    String url,
    int priority
) {

    public UserProfilePhoto toEntity(final String userUuid) {
        return UserProfilePhoto.create(userUuid, url, priority);
    }
}
