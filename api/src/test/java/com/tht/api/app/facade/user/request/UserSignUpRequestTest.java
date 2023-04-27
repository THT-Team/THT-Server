package com.tht.api.app.facade.user.request;

import static org.assertj.core.api.Assertions.assertThat;

import com.tht.api.app.entity.user.UserProfilePhoto;
import com.tht.api.app.fixture.UserSignUpRequestFixture;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserSignUpRequestTest {

    @Test
    @DisplayName("유저 프로필 저장시 우선순위는 list 의 순서 순 보장")
    void createUserPhotoList_forPriority() {
        UserSignUpRequest request = UserSignUpRequestFixture.ofPhoto(List.of("1", "2", "3"));

        List<UserProfilePhoto> userProfilePhotos = request.makeUserProfilePhotoList("user-uuid");

        for (UserProfilePhoto profilePhoto : userProfilePhotos) {
            assertThat(profilePhoto.getPriority())
                .isEqualTo(Integer.parseInt(profilePhoto.getUrl()));
        }
    }
}