package com.tht.thtadmin.acceptance;

import com.tht.thtadmin.acceptance.config.AcceptanceTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.tht.thtadmin.acceptance.LoginAcceptanceStep.로그인_요청;
import static com.tht.thtadmin.acceptance.LoginAcceptanceStep.어드민_생성;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class LoginAcceptance extends AcceptanceTest {

    @Test
    @DisplayName("로그인 성공 테스트")
    void login() {

        //회원가입
        String username = "admin 유저";
        String password = "password";
        String id = "admin_test";

        어드민_생성(id, password, username);

        //로그인
        var result = 로그인_요청(id, password);

        //then
        assertAll(
            () -> assertThat(result.statusCode()).isEqualTo(200),
            () -> assertThat(result.jsonPath().getString("username")).isEqualTo(username)
        );
    }

    @Test
    @DisplayName("로그인 실패 테스트")
    void login_fail() {

        //회원가입
        String username = "admin 유저";
        String password = "password";
        String id = "admin_test";

        //로그인
        var result = 로그인_요청(id, password);

        //then
        assertAll(
            () -> assertThat(result.statusCode()).isEqualTo(400)
        );
    }

    @Test
    @DisplayName("관리자 유저 생성 실패 테스트")
    void create_fail() {

        //회원가입
        String username = "admin 유저";
        String password = "password";
        String id = "admin_test";

        어드민_생성(id, password, username);

        //로그인
        var result = 어드민_생성(id, password, username);

        assertThat(result.statusCode()).isEqualTo(400);
    }
}
