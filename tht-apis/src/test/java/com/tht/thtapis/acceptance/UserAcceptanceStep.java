package com.tht.thtapis.acceptance;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.MediaType;

public class UserAcceptanceStep {

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
