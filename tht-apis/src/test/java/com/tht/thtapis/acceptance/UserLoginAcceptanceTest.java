package com.tht.thtapis.acceptance;

import com.tht.enums.user.Gender;
import com.tht.enums.user.SNSType;
import com.tht.thtapis.acceptance.config.AcceptanceTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.tht.thtapis.acceptance.UserAcceptanceStep.유저계정_탈퇴_요청;
import static com.tht.thtapis.acceptance.UserLoginAcceptanceStep.*;
import static com.tht.thtapis.acceptance.UserSignUpAcceptanceStep.*;
import static java.lang.Thread.sleep;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class UserLoginAcceptanceTest extends AcceptanceTest {

    @Test
    @DisplayName("유저 일반 타입 로그인 인수테스트")
    void userLogin() {

        //유저 생성
        final String username = "유저1";
        final String phoneNumber = "01012345678";
        신규유저_생성_요청_후_토큰추출(username, phoneNumber);

        //when
        final String deviceKey = "deviceKey";
        var result = 일반로그인(phoneNumber, deviceKey);

        //that
        assertAll(
            () -> assertThat(result.statusCode()).isEqualTo(200),
            () -> assertThat(result.jsonPath().getString("accessToken")).isNotEmpty()
        );
    }

    @Test
    @DisplayName("유저 SNS 타입 로그인 인수테스트")
    void userSNSLogin() {

        //sns 유저 생성
        final String phoneNumber = "01012345678";
        final String email = "email@test.com";
        final SNSType snsType = SNSType.KAKAO;
        final String snsUniqueId = "snsUniqueId";
        final String deviceKey = "deviceKey";

        SNS_유저_생성(phoneNumber, email, snsType, snsUniqueId, deviceKey);

        //when
        var result = SNS_로그인(phoneNumber, email, snsType, snsUniqueId, deviceKey);

        //that
        assertAll(
            () -> assertThat(result.statusCode()).isEqualTo(200),
            () -> assertThat(result.jsonPath().getString("accessToken")).isNotEmpty()
        );
    }

    @Test
    @DisplayName("토큰 리프레쉬 인수테스트")
    void refreshToken() throws InterruptedException {

        //유저 생성
        final String username = "유저1";
        final String phoneNumber = "01012345678";
        final String token = 신규유저_생성_요청_후_토큰추출(username, phoneNumber);

        //when
        sleep(1000);
        var result = 토큰리프레시(token);

        //then
        assertThat(result.jsonPath().getString("accessToken")).isNotEqualTo(token);
    }

    @Test
    @DisplayName("탈퇴 후, 동일한 번호로 재가입 시 정상 로그인")
    void signUpAfterWithdrawal() {

        final String phoneNumber = "01012345678";
        final String username = "유저";

        final String token = 신규유저_생성_요청_후_토큰추출(username, phoneNumber);


        //when
        var 삭제요청결과 = 유저계정_탈퇴_요청(token);
        var result = 신규유저_생성_요청(username, phoneNumber, Gender.MALE, Gender.FEMALE);

        //then
        assertThat(result.jsonPath().getString("accessToken")).isNotEqualTo(token);
        assertThat(result.statusCode()).isEqualTo(201);

    }
}
