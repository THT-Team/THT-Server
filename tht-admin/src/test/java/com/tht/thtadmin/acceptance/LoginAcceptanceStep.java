package com.tht.thtadmin.acceptance;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.springframework.http.MediaType;

import java.util.HashMap;
import java.util.Map;

public class LoginAcceptanceStep {

    public static ExtractableResponse<Response> 어드민_생성(String id, String password, String username) {

        Map<String, Object> request = new HashMap<>();
            request.put("username", username);
            request.put("password", password);
            request.put("id", id);

        return RestAssured.given().log().all()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .when()
            .body(request)
            .post("/create")
            .then().log().all()
            .extract();
    }

    public static ExtractableResponse<Response> 로그인_요청(String id, String password) {

        Map<String, Object> request = new HashMap<>();
            request.put("id", id);
            request.put("password", password);

        return RestAssured.given().log().all()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .when()
            .body(request)
            .post("/login")
            .then().log().all()
            .extract();
    }
}
