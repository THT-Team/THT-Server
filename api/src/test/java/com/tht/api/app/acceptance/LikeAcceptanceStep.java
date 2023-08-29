package com.tht.api.app.acceptance;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.springframework.http.MediaType;

public class LikeAcceptanceStep {

    public static ExtractableResponse<Response> 좋아요_요청(String accessToken, String favoriteUserUuid, long dailyFalling) {
        return RestAssured.given().log().all()
            .auth().oauth2(accessToken)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .when()
            .post("/i-like-you/{favorite-user-uuid}/{daily-topic-idx}",
                favoriteUserUuid,
                dailyFalling
            )
            .then().log().all()
            .extract();
    }
}
