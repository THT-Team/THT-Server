package com.tht.thtadmin.fixture.user;

import com.tht.enums.agreement.AgreementCategory;
import com.tht.enums.user.Gender;
import com.tht.enums.user.SNSType;
import com.tht.enums.user.UserFrequency;
import com.tht.enums.user.UserReligion;
import com.tht.thtadmin.ui.user.response.UserDetailResponse;

import java.util.List;
import java.util.Map;

public class UserDetailResponseFixture {

    private static final String phoneNumber = "01012345678";
    private static final String username = "유저이름입니다";
    private static final String birthDay = "1997.11.04";
    private static final String email = "email@tht.com";
    private static final List<SNSType> snsSignUpList = List.of(SNSType.KAKAO, SNSType.NAVER, SNSType.GOOGLE);
    private static final Map<AgreementCategory, Boolean> serviceAgreeList = Map.of(
        AgreementCategory.LOCATION_SERVICE_AGREE, true,
        AgreementCategory.SERVICE_USE_AGREE, true,
        AgreementCategory.MARKETING_AGREE, true,
        AgreementCategory.PERSONAL_PRIVACY_INFO_AGREE, true
    );
    private static final Gender gender = Gender.MALE;
    private static final Gender preferGender = Gender.BISEXUAL;
    private static final Map<Integer, String> profileUrl = Map.of(
        1, "대표사진-url",
        2, "프로필-url",
        3, "프로필-url"
    );
    private static final int tall = 180;
    private static final UserFrequency drinkStatus = UserFrequency.FREQUENTLY;
    private static final UserReligion religion = UserReligion.BUDDHISM;
    private static final UserFrequency smokingStatus = UserFrequency.SOMETIMES;
    private static final String userLocation = "집주소";
    private static final List<String> interests = List.of("관심사1", "관심사2", "관심사3");
    private static final List<String> idealTypes = List.of("이상형1", "이상형2", "이상형3");

    public static UserDetailResponse make() {
        return new UserDetailResponse(phoneNumber, username, birthDay, email, snsSignUpList, serviceAgreeList, gender,
            preferGender, profileUrl, tall, drinkStatus, religion, smokingStatus, userLocation, interests, idealTypes);
    }

}
