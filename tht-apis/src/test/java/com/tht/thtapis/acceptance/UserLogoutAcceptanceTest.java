package com.tht.thtapis.acceptance;

import com.tht.domain.entity.user.User;
import com.tht.enums.user.SNSType;
import com.tht.thtapis.acceptance.config.AcceptanceTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.tht.thtapis.acceptance.UserLoginAcceptanceStep.일반로그인;
import static com.tht.thtapis.acceptance.UserLogoutAcceptanceStep.로그아웃_요청;
import static com.tht.thtapis.acceptance.UserSignUpAcceptanceStep.SNS_유저_생성;
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

    @Test
    @DisplayName("로그아웃 후 일반 로그인 테스트")
    void normalLoginAfterLogout() {

        String phoneNumber = "1113332222";
        String username = "username";
        String token = 신규유저_생성_요청_후_토큰추출(username, phoneNumber);

        로그아웃_요청(token);

        //when
        일반로그인(phoneNumber, "dd");

        //then
        User user = 유저정보조회(phoneNumber);
        assertThat(user.getIsLogin()).isTrue();
    }

    @Test
    @DisplayName("로그아웃 후 sns 로그인 테스트")
    void snsLoginAfterLogout() {

        //sns 유저 생성
        final String phoneNumber = "01012345678";
        final String email = "email@test.com";
        final SNSType snsType = SNSType.KAKAO;
        final String snsUniqueId = "snsUniqueId";
        final String deviceKey = "deviceKey";

        var response = SNS_유저_생성(phoneNumber, email, snsType, snsUniqueId, deviceKey);
        String token = response.jsonPath().getString("accessToken");

        로그아웃_요청(token);

        //when
        일반로그인(phoneNumber, "ㅇㄹ");

        //then
        User user = 유저정보조회(phoneNumber);
        assertThat(user.getIsLogin()).isTrue();
    }
}
