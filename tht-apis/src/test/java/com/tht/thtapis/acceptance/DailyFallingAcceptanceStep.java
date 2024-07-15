package com.tht.thtapis.acceptance;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.springframework.http.MediaType;

public class DailyFallingAcceptanceStep {

    public static ExtractableResponse<Response> 그날의주제어_선택여부_조회_요청(String accessToken) {
        return RestAssured.given().log().all()
                .auth().oauth2(accessToken)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get("/check/is-choose-daily-topic")
                .then().log().all()
                .extract();
    }

}
