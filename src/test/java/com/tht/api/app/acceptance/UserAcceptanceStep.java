package com.tht.api.app.acceptance;

import com.tht.api.app.entity.enums.Gender;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.springframework.http.MediaType;

public class UserAcceptanceStep {

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

        return map;
    }

    public static ExtractableResponse<Response> 그날의_대화토픽_선택_요청(final long fallingIdx,
        final String accessToken) {
        return RestAssured.given().log().all()
            .auth().oauth2(accessToken)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .when().post("/falling/choice/daily-keyword/" + fallingIdx)
            .then().log().all().extract();
    }

    public static ExtractableResponse<Response> 메인화면_조회_요청(final String accessToken) {

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("alreadySeenUserUuidList", List.of());

        return RestAssured.given().log().all()
            .auth().oauth2(accessToken)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body(requestBody)
            .when().post("/main/daily-falling/users")
            .then().log().all().extract();
    }

    public static ExtractableResponse<Response> 유저계정_탈퇴_요청(final String accessToken) {

        Map<String, String> request = new HashMap<>();
        request.put("reason", "탈퇴 이유");
        request.put("feedBack", "피드백");

        return RestAssured.given().log().all()
            .auth().oauth2(accessToken)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body(request)
            .when().delete("/user/account-withdrawal")
            .then().log().all().extract();
    }

    public static ExtractableResponse<Response> 유저_기기_연락처_차단_요청(String accessToken, Map<String, Object> request) {

        return RestAssured.given().log().all()
            .auth().oauth2(accessToken)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body(request)
            .when().post("/user/friend-contact-list")
            .then().log().all().extract();
    }

    public static ExtractableResponse<Response> 유저_기기_연락처_차단_리스트_조회_요청(String accessToken) {

        return RestAssured.given().log().all()
            .auth().oauth2(accessToken)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .when().get("/user/friend-contact-list")
            .then().log().all().extract();
    }

    public static ExtractableResponse<Response> 유저_차단_요청(String accessToken, String blockUserUuid) {

        return RestAssured.given().log().all()
            .auth().oauth2(accessToken)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .when().post("/user/block/{block-user-uuid}", blockUserUuid)
            .then().log().all().extract();
    }
}
