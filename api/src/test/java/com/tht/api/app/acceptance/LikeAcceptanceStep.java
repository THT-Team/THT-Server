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

    public static ExtractableResponse<Response> 좋아요_리스트_조회_요청(String accessToken) {
        return RestAssured.given().log().all()
            .auth().oauth2(accessToken)
            .when().get("/like/receives")
            .then().log().all()
            .extract();
    }

    public static ExtractableResponse<Response> 좋아요_거절_요청(String accessToken, long likeIdx) {

        return RestAssured.given().log().all()
            .auth().oauth2(accessToken)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .queryParam("likeIdx", likeIdx)
            .when().post("/like/reject")
            .then().log().all()
            .extract();
    }
}
