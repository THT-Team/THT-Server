package com.tht.thtapis.facade.user.response;


import com.tht.domain.entity.user.UserProfilePhoto;
import com.tht.domain.entity.user.mapper.UserProfilePhotoMapper;

public record UserProfilePhotoResponse(
    String url,
    int priority
) {

    public static UserProfilePhotoResponse of(final UserProfilePhotoMapper mapper) {
        return new UserProfilePhotoResponse(mapper.url(), mapper.priority());
    }

    public static UserProfilePhotoResponse of(final UserProfilePhoto entity) {
        return new UserProfilePhotoResponse(entity.getUrl(), entity.getPriority());
    }
}
