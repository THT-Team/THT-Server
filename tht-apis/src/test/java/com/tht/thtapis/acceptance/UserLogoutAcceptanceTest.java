package com.tht.thtapis.acceptance;

import com.tht.domain.entity.user.User;
import com.tht.thtapis.acceptance.config.AcceptanceTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.tht.thtapis.acceptance.UserLogoutAcceptanceStep.로그아웃_요청;
import static com.tht.thtapis.acceptance.UserSignUpAcceptanceStep.신규유저_생성_요청_후_토큰추출;
import static org.assertj.core.api.Assertions.assertThat;

class UserLogoutAcceptanceTest extends AcceptanceTest {

    @Test
    @DisplayName("유저 로그아웃 성공 인수테스트")
    void userLogout() {

        String phoneNumber = "1113332222";
        String username = "username";
        String token = 신규유저_생성_요청_후_토큰추출(username, phoneNumber);

        //로그아웃
        var response = 로그아웃_요청(token);
        assertThat(response.statusCode()).isEqualTo(200);

        User user = 유저정보조회(phoneNumber);
        assertThat(user.getIsLogin()).isFalse();

    }
}
