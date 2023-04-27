package com.tht.api.app.entity.user;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserProfilePhotoTest {

    private final static String userUuid = "test-uuid";
    private final static String url = "url";
    private final static Integer priority = 1;


    @Test
    @DisplayName("유저 사진 엔티티 생성 테스트")
    void create() {

        UserProfilePhoto profilePhoto = UserProfilePhoto.create(userUuid, url, priority);
        assertThat(profilePhoto.getUserUuid()).isEqualTo(userUuid);
        assertThat(profilePhoto.getUrl()).isEqualTo(url);
        assertThat(profilePhoto.getPriority()).isEqualTo(priority);
    }
}