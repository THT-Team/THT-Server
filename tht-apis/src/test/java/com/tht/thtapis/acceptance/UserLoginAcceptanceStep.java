package com.tht.thtapis.acceptance;

import com.tht.enums.user.SNSType;
import com.tht.thtapis.acceptance.config.AcceptanceTest;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.springframework.http.MediaType;

import java.util.HashMap;
import java.util.Map;

class UserLoginAcceptanceStep extends AcceptanceTest {

    public static ExtractableResponse<Response> 일반로그인(final String phoneNumber, final String deviceKey){


        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("phoneNumber", phoneNumber);
        requestBody.put("deviceKey", deviceKey);

        return RestAssured.given().log().all()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body(requestBody)
            .when().post("/users/login/normal")
            .then().log().all().extract();
    }

    public static ExtractableResponse<Response> SNS_로그인(String phoneNumber, String email, SNSType snsType, String snsUniqueId, String deviceKey) {

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("email", email);
        requestBody.put("snsType", snsType.name());
        requestBody.put("snsUniqueId", snsUniqueId);
        requestBody.put("deviceKey", deviceKey);

        return RestAssured.given().log().all()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body(requestBody)
            .when().post("/users/login/sns")
            .then().log().all().extract();

    }

    public static ExtractableResponse<Response> 토큰리프레시(String accessToken) {

        return RestAssured.given().log().all()
            .auth().oauth2(accessToken)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .when().post("/users/login/refresh")
            .then().log().all().extract();

    }
}
