package com.tht.thtapis.acceptance;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

class UserLogoutAcceptanceStep {

    public static ExtractableResponse<Response> 로그아웃_요청(String accessToken) {

        return RestAssured.given().log().all()
            .auth().oauth2(accessToken)
            .when().post("/user/logout")
            .then().log().all()
            .extract();
    }
}
