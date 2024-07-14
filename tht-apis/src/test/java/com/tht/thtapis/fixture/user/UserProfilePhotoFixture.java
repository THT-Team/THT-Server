package com.tht.thtapis.fixture.user;


import com.tht.infra.user.mapper.UserProfilePhotoMapper;
import com.tht.thtapis.facade.user.response.UserProfilePhotoResponse;

public class UserProfilePhotoFixture {

    private static final Long idx = 1L;
    private static final String userUuid = "user uuid";
    private static final String url ="img_url" ;
    private static final Integer priority = 1;

    public static UserProfilePhotoMapper mapperMake() {
        return new UserProfilePhotoMapper(idx, userUuid, url, priority);
    }

    public static UserProfilePhotoResponse responseMake() {
        return new UserProfilePhotoResponse(url, priority);
    }
}
