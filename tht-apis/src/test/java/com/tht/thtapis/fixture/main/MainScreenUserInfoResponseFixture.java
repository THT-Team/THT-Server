package com.tht.thtapis.fixture.main;

import com.tht.thtapis.facade.idealtype.response.IdealTypeResponse;
import com.tht.thtapis.facade.interest.response.InterestResponse;
import com.tht.thtapis.facade.user.response.MainScreenUserInfoResponse;
import com.tht.thtapis.facade.user.response.UserProfilePhotoResponse;
import com.tht.thtapis.fixture.meta.IdealTypeFixture;
import com.tht.thtapis.fixture.meta.InterestFixture;
import com.tht.thtapis.fixture.user.UserProfilePhotoFixture;

import java.util.List;

public class MainScreenUserInfoResponseFixture {

    private static final String username = "매칭된 유저 이름";
    private static final String userUuid = "매칭된 유저 uuid";
    private static final int age = 24;
    private static final String address = "인천광역시 부평구 ";
    private static final boolean isBirthDay = true;
    private static final List<IdealTypeResponse> idealTypeResponseList = List.of(
        IdealTypeFixture.responseMake());
    private static final List<InterestResponse> interestResponses = List.of(
        InterestFixture.responseMake());

    private static final List<UserProfilePhotoResponse> userProfilePhotoResponses = List.of(
        UserProfilePhotoFixture.responseMake());
    private static final String introduction = "유저 자기소개";
    private static final long userDailyFallingIdx = 1;
    private static final int distance = 1200;

    public static MainScreenUserInfoResponse make() {
        return new MainScreenUserInfoResponse(
            username,
            userUuid,
            age,
            address,
            isBirthDay,
            idealTypeResponseList,
            interestResponses,
            userProfilePhotoResponses,
            introduction,
            userDailyFallingIdx,
            distance
        );
    }
}
