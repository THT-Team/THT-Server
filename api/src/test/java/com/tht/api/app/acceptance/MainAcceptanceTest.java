package com.tht.api.app.acceptance;

import static com.tht.api.app.acceptance.MainAcceptanceStep.메인화면_매칭유저_조회_요청;
import static com.tht.api.app.acceptance.UserAcceptanceStep.그날의_대화토픽_선택_요청;
import static com.tht.api.app.acceptance.UserAcceptanceStep.신규유저_생성_요청_후_토큰추출;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import com.tht.api.app.acceptance.config.AcceptanceTest;
import com.tht.api.app.entity.enums.Gender;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MainAcceptanceTest extends AcceptanceTest {


    /**
     * 메인화면 조회 인수테스트 시나리오
     * <br>
     * <br> scenario
     * <br> 1. 유저가 그날의 데일리 토픽을 선택한다.
     * <br> 2. 같은 데일리 토픽을 선택한 유저들을 조회한다.
     * <br> 3. 조회되는 유저들은 토픽을 선택한 순서대로 조회된다.
     * <br>
     * <br> filter
     * <br> 1. 서로의 선호성별과 성별이 매칭되는 유저만 조회된다.
     * <br> 2. 이미 해당 주제어에서 좋아요한 사람은 제외된다.
     * <br> 3. 어딘가에서 싫어요한 유저는 7일동안 노출에서 제외된다.
     */
    @DisplayName("메인화면 매칭된 유저리스트 조회 인수테스트")
    @Test
    void getMainUserList() {

        //given
        var 나 = 신규유저_생성_요청_후_토큰추출("나", "01065689787", Gender.MALE, Gender.FEMALE);
        var 유저1 = 신규유저_생성_요청_후_토큰추출("유저1", "01065689737", Gender.FEMALE, Gender.BISEXUAL);
        var 유저2 = 신규유저_생성_요청_후_토큰추출("유저2", "01065681414", Gender.FEMALE, Gender.MALE);
        var 유저3 = 신규유저_생성_요청_후_토큰추출("유저3", "01065681411", Gender.FEMALE, Gender.FEMALE);
        var 유저4 = 신규유저_생성_요청_후_토큰추출("유저4", "01065681444", Gender.MALE, Gender.BISEXUAL);

        var dailyFalling = 그날의주제어_생성_요청();

        //given
        그날의_대화토픽_선택_요청(dailyFalling.getIdx(), 나);
        그날의_대화토픽_선택_요청(dailyFalling.getIdx(), 유저1);
        그날의_대화토픽_선택_요청(dailyFalling.getIdx(), 유저2);
        그날의_대화토픽_선택_요청(dailyFalling.getIdx(), 유저3);
        그날의_대화토픽_선택_요청(dailyFalling.getIdx(), 유저4);

        //when
        var response = 메인화면_매칭유저_조회_요청(나, null, null);

        //then
        assertAll(
            () -> assertThat(response.statusCode()).isEqualTo(200),
            () -> assertThat(response.jsonPath().getList("userInfos.username"))
                .containsExactly("유저1", "유저3")
        );

    }

    @DisplayName("내가 싫어요를 누른 상대도 7일이 지나면 다시 표출된다.")
    @Test
    void test() {

    }
}
