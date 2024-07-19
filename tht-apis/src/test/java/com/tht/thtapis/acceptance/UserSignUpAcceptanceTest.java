package com.tht.thtapis.acceptance;

import com.tht.enums.user.Gender;
import com.tht.thtapis.acceptance.config.AcceptanceTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.tht.thtapis.acceptance.UserAcceptanceStep.유저계정_탈퇴_요청;
import static com.tht.thtapis.acceptance.UserSignUpAcceptanceStep.신규유저_생성_요청;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class UserSignUpAcceptanceTest extends AcceptanceTest {

    @Test
    @DisplayName("회원 탈퇴 후 재가입 테스트")
    void reSignUp() {

        var phoneNumber = "33833401234";
        var userName = "신규유저";
        var 신규유저 = 신규유저_생성_요청(userName, phoneNumber, Gender.MALE, Gender.FEMALE);

        String accessToken = 신규유저.jsonPath().getString("accessToken");
        String userUuid = 신규유저.jsonPath().getString("userUuid");

        유저계정_탈퇴_요청(accessToken);

        //when
        var result = 신규유저_생성_요청(userName, phoneNumber, Gender.MALE, Gender.FEMALE);

        //then
        assertAll(
            () -> assertThat(result.statusCode()).isEqualTo(201),
            () -> assertThat(result.jsonPath().getString("accessToken")).isNotEmpty(),
            () -> assertThat(result.jsonPath().getString("userUuid")).isNotEqualTo(userUuid)
        );
    }
}
