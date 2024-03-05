package com.tht.api.app.unit.fixture.user;

import com.tht.api.app.entity.enums.Gender;
import com.tht.api.app.entity.enums.SNSType;
import com.tht.api.app.facade.user.request.UserAgreementRequest;
import com.tht.api.app.facade.user.request.UserLocationRequest;
import com.tht.api.app.facade.user.request.UserSignUpRequest;
import java.util.List;

public class UserSignUpRequestFixture {

    private final static String phoneNumber = "01012345678";

    private final static String username = "user-nick-name";

    private final static String email = "email@test.com";

    private final static String birthDay = "2004.11.11";

    private final static String gender = Gender.MALE.name();

    private final static String preferGender = Gender.FEMALE.name();

    private final static String introduction = "자기소개 ~~";

    private final static String deviceKey = "device-key-value";

    private final static UserAgreementRequest agreement = UserAgreementRequestFixture.make();

    private final static UserLocationRequest locationRequest = UserLocationRequestFixture.make();

    private final static List<String> photoList = List.of("url1", "url2", "url3");

    private final static List<Integer> interestList;

    static {
        int interests1 = 1;
        int interests2 = 2;
        int interests3 = 3;
        interestList = List.of(interests1, interests2, interests3);
    }

    private final static List<Integer> idealTypeList;

    static {
        int idealType1 = 1;
        int idealType2 = 2;
        int idealType3 = 3;
        idealTypeList = List.of(idealType1, idealType2, idealType3);
    }

    private final static SNSType snsType = SNSType.NORMAL;

    private static final String snsUniqueId = "snsUniqueId";

    private UserSignUpRequestFixture() {}

    public static UserSignUpRequest make() {
        return new UserSignUpRequest(phoneNumber, username, email, birthDay, gender, preferGender,
            introduction, deviceKey, agreement, locationRequest, photoList, interestList,
            idealTypeList, snsType.name(), snsUniqueId);
    }

    public static UserSignUpRequest ofInterest(final List<Integer> interestList) {
        return new UserSignUpRequest(phoneNumber, username, email, birthDay, gender, preferGender,
            introduction, deviceKey, agreement, locationRequest, photoList, interestList,
            idealTypeList, snsType.name(), snsUniqueId);
    }

    public static UserSignUpRequest ofIdealType(final List<Integer> idealTypeList) {
        return new UserSignUpRequest(phoneNumber, username, email, birthDay, gender, preferGender,
            introduction, deviceKey, agreement, locationRequest, photoList, interestList,
            idealTypeList, snsType.name(), snsUniqueId);
    }

    public static UserSignUpRequest ofPhoto(final List<String> photoList) {
        return new UserSignUpRequest(phoneNumber, username, email, birthDay, gender, preferGender,
            introduction, deviceKey, agreement, locationRequest, photoList, interestList,
            idealTypeList, snsType.name(), snsUniqueId);
    }

    public static UserSignUpRequest ofSNSType(final String snsType) {
        return new UserSignUpRequest(phoneNumber, username, email, birthDay, gender, preferGender,
            introduction, deviceKey, agreement, locationRequest, photoList, interestList,
            idealTypeList, snsType, snsUniqueId);
    }
}
