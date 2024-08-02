package com.tht.thtadmin.fixture.user;

import com.tht.enums.EntityState;
import com.tht.thtadmin.ui.user.response.UserSimpleListResponse;
import com.tht.thtcommonutils.utils.CustomDateFormatUtils;

import java.time.LocalDateTime;

public class UserSimpleListResponseFixture {

        private static final String username = "유저 닉네밈";
        private static final String profileUrl = "유저 프로필 사진";
        private static final String uuid = "user uuid";
        private static final String createdAt = LocalDateTime.now().format(CustomDateFormatUtils.getDateTimeInstance());
        private static final EntityState sate = EntityState.ACTIVE;

    public static UserSimpleListResponse make() {
        return new UserSimpleListResponse(username, profileUrl, uuid, createdAt, sate);
    }
}
