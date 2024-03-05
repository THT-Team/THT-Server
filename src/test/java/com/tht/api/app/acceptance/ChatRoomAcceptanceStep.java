package com.tht.api.app.acceptance;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.springframework.http.MediaType;

class ChatRoomAcceptanceStep {

    public static ExtractableResponse<Response> 채팅방_리스트_조회_요청(String accessToken) {

        return RestAssured.given().log().all()
            .auth().oauth2(accessToken)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .when()
            .get("chat/rooms")
            .then().log().all()
            .extract();
    }

    public static ExtractableResponse<Response> 채팅방_나가기_요청(String accessToken, long chatRoomIdx) {

        return RestAssured.given().log().all()
            .auth().oauth2(accessToken)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .when()
            .post("/chat/out/room/{chat-room-idx}", chatRoomIdx)
            .then().log().all()
            .extract();
    }

    public static ExtractableResponse<Response> 채팅방_상세조회_요청(String accessToken, Long chatRoomIdx) {

        return RestAssured.given().log().all()
            .auth().oauth2(accessToken)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .when()
            .get("/chat/room/{chat-room-idx}", chatRoomIdx)
            .then().log().all()
            .extract();
    }
}