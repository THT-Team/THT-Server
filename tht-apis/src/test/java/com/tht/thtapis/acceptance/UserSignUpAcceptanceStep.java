package com.tht.thtapis.acceptance;

import com.tht.infra.user.enums.Gender;
import com.tht.infra.user.enums.SNSType;
import com.tht.infra.user.enums.UserFrequency;
import com.tht.infra.user.enums.UserReligion;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.springframework.http.MediaType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class UserSignUpAcceptanceStep {

    public static String 신규유저_생성_요청_후_토큰추출(final String username, final String phoneNumber) {
        return 신규유저_생성_요청(username, phoneNumber, null, null).jsonPath().getString("accessToken");
    }

    public static String 신규유저_생성_요청_후_토큰추출(final String username, final String phoneNumber,
                                           final Gender gender, final Gender preferGender) {

        return 신규유저_생성_요청(username, phoneNumber, gender, preferGender).jsonPath().getString("accessToken");
    }

    public static ExtractableResponse<Response> 신규유저_생성_요청(final String username,
                                                           final String phoneNumber, final Gender gender, final Gender preferGender) {

        return RestAssured.given().log().all()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body(createUserJoinRequest(username, phoneNumber, gender, preferGender))
            .when().post("/users/join/signup")
            .then().log().all().extract();
    }

    private static Map<String, Object> createUserJoinRequest(final String username,
                                                             final String phoneNumber, final Gender gender, final Gender preferGender) {

        Map<String, String> agreement = new HashMap<>();
        agreement.put("serviceUseAgree", "true");
        agreement.put("personalPrivacyInfoAgree", "true");
        agreement.put("locationServiceAgree", "true");
        agreement.put("marketingAgree", "true");

        Map<String, String> locationRequest = new HashMap<>();
        locationRequest.put("address", "서울특별시 힘찬구 열심동");
        locationRequest.put("regionCode", "110444103");
        locationRequest.put("lat", "37.5");
        locationRequest.put("lon", "127.7");

        Map<String, Object> map = new HashMap<>();

        map.put("phoneNumber", phoneNumber);
        map.put("username", username);
        map.put("email", "email@test.com");
        map.put("birthDay", "2004.11.11");
        map.put("gender", Objects.isNull(gender) ? "MALE" : gender.name());
        map.put("preferGender", Objects.isNull(preferGender) ? Gender.BISEXUAL.name() : preferGender.name());
        map.put("introduction", "자기소개");
        map.put("deviceKey", "개인 디바이스 고유 키");
        map.put("agreement", agreement);
        map.put("locationRequest", locationRequest);
        map.put("photoList", List.of("url1", "url2", "url3"));
        map.put("interestList", List.of(1, 2, 3));
        map.put("idealTypeList", List.of(1, 2, 3));
        map.put("snsType", "NORMAL");
        map.put("snsUniqueId", "snsUniqueId");
        map.put("tall", 180);
        map.put("drinking", UserFrequency.SOMETIMES.name());
        map.put("smoking", UserFrequency.NONE.name());
        map.put("religion", UserReligion.WON_BUDDHISM.name());

        return map;
    }

    public static ExtractableResponse<Response> SNS_유저_생성(final String phoneNumber, final String email, final SNSType snsType,
                                                          final String snsUniqueId, final String deviceKey) {

        RestAssured.given().log().all()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body(createSNSUserJoinRequest(phoneNumber, email, snsType, snsUniqueId, deviceKey))
            .when().post("/users/join/signup")
            .then().log().all().extract();

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("email", email);
        requestBody.put("snsType", snsType.name());
        requestBody.put("snsUniqueId", snsUniqueId);
        requestBody.put("deviceKey", deviceKey);

        return RestAssured.given().log().all()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body(requestBody)
            .when().post("/users/join/signup/sns")
            .then().log().all().extract();
    }

    private static Map<String, Object> createSNSUserJoinRequest(final String phoneNumber, final String email, final SNSType snsType,
                                                                final String snsUniqueId, final String deviceKeys) {

        Map<String, String> agreement = new HashMap<>();
        agreement.put("serviceUseAgree", "true");
        agreement.put("personalPrivacyInfoAgree", "true");
        agreement.put("locationServiceAgree", "true");
        agreement.put("marketingAgree", "true");

        Map<String, String> locationRequest = new HashMap<>();
        locationRequest.put("address", "서울특별시 힘찬구 열심동");
        locationRequest.put("regionCode", "110444103");
        locationRequest.put("lat", "37.5");
        locationRequest.put("lon", "127.7");

        Map<String, Object> map = new HashMap<>();

        map.put("phoneNumber", phoneNumber);
        map.put("username", "유저1");
        map.put("email", email);
        map.put("birthDay", "2004.11.11");
        map.put("gender", "MALE");
        map.put("preferGender", "BISEXUAL");
        map.put("introduction", "자기소개");
        map.put("deviceKey", deviceKeys);
        map.put("agreement", agreement);
        map.put("locationRequest", locationRequest);
        map.put("photoList", List.of("url1", "url2", "url3"));
        map.put("interestList", List.of(1, 2, 3));
        map.put("idealTypeList", List.of(1, 2, 3));
        map.put("snsType", snsType.name());
        map.put("snsUniqueId", snsUniqueId);
        map.put("tall", 180);
        map.put("drinking", UserFrequency.SOMETIMES.name());
        map.put("smoking", UserFrequency.NONE.name());
        map.put("religion", UserReligion.WON_BUDDHISM.name());

        return map;
    }
}
