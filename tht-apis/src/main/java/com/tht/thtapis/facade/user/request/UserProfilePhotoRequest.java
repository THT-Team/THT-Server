package com.tht.thtapis.facade.user.request;


import com.tht.domain.entity.user.UserProfilePhoto;

public record UserProfilePhotoRequest(
    String url,
    int priority
) {

    public UserProfilePhoto toEntity(final String userUuid) {
        return UserProfilePhoto.create(userUuid, url, priority);
    }
}
