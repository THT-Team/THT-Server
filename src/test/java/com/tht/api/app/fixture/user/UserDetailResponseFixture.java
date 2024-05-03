package com.tht.api.app.fixture.user;

import com.tht.api.app.entity.enums.Gender;
import com.tht.api.app.entity.enums.UserFrequency;
import com.tht.api.app.entity.enums.UserReligion;
import com.tht.api.app.facade.idealtype.response.IdealTypeResponse;
import com.tht.api.app.facade.interest.response.InterestResponse;
import com.tht.api.app.facade.user.response.UserAgreementResponse;
import com.tht.api.app.facade.user.response.UserDetailResponse;
import com.tht.api.app.facade.user.response.UserProfilePhotoResponse;
import com.tht.api.app.fixture.meta.IdealTypeFixture;
import com.tht.api.app.fixture.meta.InterestFixture;

import java.util.List;

public class UserDetailResponseFixture {

    private static final String username = "유저이름";
    private static final String userUuid = "makeUuid-2mnk-41";
    private static final int age = 345;
    private static final String introduction = "내 나이는 서른마흔다섯";
    private static final String address = "서울시 성동구 저쩌구 동작구";
    private static final String phoneNumber = "01010041004";
    private static final String email = "beenZino@naver.com";
    private static final int tall = 170;
    private static final Gender gender = Gender.MALE;
    private static final Gender preferGender = Gender.FEMALE;
    private static final UserFrequency smoking = UserFrequency.NONE;
    private static final UserFrequency drinking = UserFrequency.FREQUENTLY;
    private static final UserReligion religion = UserReligion.BUDDHISM;
    private static final List<IdealTypeResponse> idealTypeList = List.of(
            IdealTypeFixture.responseMake()
    );
    private static final List<InterestResponse> interestsList = List.of(
            InterestFixture.responseMake()
    );
    private static final List<UserProfilePhotoResponse> userProfilePhotos = List.of(
            UserProfilePhotoFixture.responseMake()
    );

    private static final UserAgreementResponse userAgreements = UserAgreementResponseFixture.make();

    public static UserDetailResponse make() {
        return new UserDetailResponse(
                username,
                userUuid,
                age,
                introduction,
                address,
                phoneNumber,
                email,
                gender,
                preferGender,
                tall,
                smoking,
                drinking,
                religion,
                idealTypeList,
                interestsList,
                userProfilePhotos,
                userAgreements
        );
    }
}
