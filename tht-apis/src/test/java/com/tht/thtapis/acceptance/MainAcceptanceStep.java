package com.tht.thtapis.acceptance;

import com.tht.thtapis.facade.user.request.MainScreenUserInfoRequest;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.springframework.http.MediaType;

public class MainAcceptanceStep {

    public static ExtractableResponse<Response> 메인화면_매칭유저_조회_요청(final String accessToken,
        final Long userDailyFallingCourserIdx, Integer size) {
        return RestAssured.given().log().all()
            .auth().oauth2(accessToken)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body(new MainScreenUserInfoRequest(userDailyFallingCourserIdx, size))
            .when().post("/main/daily-falling/users")
            .then().log().all().extract();
    }
}
