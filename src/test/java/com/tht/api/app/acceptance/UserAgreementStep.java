package com.tht.api.app.acceptance;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.springframework.http.MediaType;

import java.util.HashMap;
import java.util.Map;

public class UserAgreementStep {

    public static ExtractableResponse<Response> 유저약관동의내역_수정(String accessToken, String agreementName, boolean value) {

        Map<String, Object> request = new HashMap<>();
        request.put("agreementName", agreementName);
        request.put("value", value);

        return RestAssured.given().log().all()
                .auth().oauth2(accessToken)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request)
                .when().patch("/user/agreement")
                .then().log().all().extract();
    }


    public static ExtractableResponse<Response> 유저_상세정보_조회(final String accessToken) {

        return RestAssured.given().log().all()
                .auth().oauth2(accessToken)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().get("/user")
                .then().log().all().extract();
    }
}
