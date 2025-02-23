package com.tht.thtapis.fixture.user;

import com.tht.enums.user.Gender;
import com.tht.enums.user.UserFrequency;
import com.tht.enums.user.UserReligion;
import com.tht.thtapis.facade.idealtype.response.IdealTypeResponse;
import com.tht.thtapis.facade.interest.response.InterestResponse;
import com.tht.thtapis.facade.user.response.UserAgreementResponse;
import com.tht.thtapis.facade.user.response.UserDetailResponse;
import com.tht.thtapis.facade.user.response.UserProfilePhotoResponse;
import com.tht.thtapis.fixture.meta.IdealTypeFixture;
import com.tht.thtapis.fixture.meta.InterestFixture;

import java.time.LocalDate;
import java.util.List;

public class UserDetailResponseFixture {

    private static final String username = "유저이름";
    private static final String userUuid = "makeUuid-2mnk-41";
    private static final int age = 345;
    private static final String introduction = "내 나이는 서른마흔다섯";
    private static final String address = "서울시 성동구 저쩌구 동작구";
    private static final String phoneNumber = "01010041004";
    private static final String email = "beenZino@naver.com";
    private static final LocalDate birthDay = LocalDate.now();
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
                birthDay,
                gender,
                preferGender,
                tall,
                smoking.getDesc(),
                drinking.getDesc(),
                religion.getDesc(),
                idealTypeList,
                interestsList,
                userProfilePhotos,
                userAgreements
        );
    }
}
