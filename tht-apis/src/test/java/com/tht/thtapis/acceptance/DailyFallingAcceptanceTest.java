package com.tht.thtapis.acceptance;

import com.tht.thtapis.acceptance.config.AcceptanceTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.tht.thtapis.acceptance.DailyFallingAcceptanceStep.그날의주제어_선택여부_조회_요청;
import static com.tht.thtapis.acceptance.UserAcceptanceStep.그날의_대화토픽_선택_요청;
import static com.tht.thtapis.acceptance.UserSignUpAcceptanceStep.신규유저_생성_요청_후_토큰추출;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class DailyFallingAcceptanceTest extends AcceptanceTest {

    @DisplayName("유저의 오늘의 토픽주제 선택여부 확인 - 미선택시 false")
    @Test
    public void isChooseDailyTopicFalse() {

        //give
        var 나 = 신규유저_생성_요청_후_토큰추출("일반 사용자1", "01065689787");
        그날의주제어_생성_요청();

        //when
        var response = 그날의주제어_선택여부_조회_요청(나);

        //then
        assertAll(
                () -> assertThat(response.statusCode()).isEqualTo(200),
                () -> assertThat(response.jsonPath().getBoolean("isChoose")).isFalse()

        );

    }

    @DisplayName("유저의 오늘의 토픽주제 선택여부 확인 - 선택시 true")
    @Test
    public void isChooseDailyTopicTrue() {

        //give
        var 나 = 신규유저_생성_요청_후_토큰추출("일반 사용자1", "01065689787");
        var dailyFalling = 그날의주제어_생성_요청();

        그날의_대화토픽_선택_요청(dailyFalling.getIdx(), 나);

        //when
        var response = 그날의주제어_선택여부_조회_요청(나);

        //then
        assertAll(
                () -> assertThat(response.statusCode()).isEqualTo(200),
                () -> assertThat(response.jsonPath().getBoolean("isChoose")).isTrue()

        );

    }
}
