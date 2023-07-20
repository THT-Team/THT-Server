package com.tht.api.app.fixture.user;

import com.tht.api.app.facade.idealtype.response.IdealTypeResponse;
import com.tht.api.app.facade.interest.response.InterestResponse;
import com.tht.api.app.facade.user.response.UserDetailResponse;
import com.tht.api.app.facade.user.response.UserProfilePhotoResponse;
import com.tht.api.app.fixture.meta.IdealTypeFixture;
import com.tht.api.app.fixture.meta.InterestFixture;
import java.util.List;

public class UserDetailResponseFixture {

    private static final String username = "유저이름";
    private static final int age = 345;
    private static final String introduction = "내 나이는 서른마흔다섯";
    private static final String address = "서울시 성동구 저쩌구 동작구";
    private static final List<IdealTypeResponse> idealTypeList = List.of(
        IdealTypeFixture.responseMake()
    );
    private static final List<InterestResponse> interestsList = List.of(
        InterestFixture.responseMake()
    );
    private static final List<UserProfilePhotoResponse> userProfilePhotos = List.of(
        UserProfilePhotoFixture.responseMake()
    );

    public static UserDetailResponse make() {
        return new UserDetailResponse(
            username,
            age,
            introduction,
            address,
            idealTypeList,
            interestsList,
            userProfilePhotos
        );
    }
}
