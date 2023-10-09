package com.tht.api.app.acceptance;

import static com.tht.api.app.acceptance.UserAcceptanceStep.신규유저_생성_요청_후_토큰추출;

import com.tht.api.app.acceptance.config.AcceptanceTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MainAcceptanceTest extends AcceptanceTest {

    //todo. 메인화면 리팩토링

    @DisplayName("내가 싫어요를 누른 상대는 메인화면 유저 리스트에 조회되지 않아야한다.")
    @Test
    void getMainUserList() {

        //given
        var 나 = 신규유저_생성_요청_후_토큰추출("나", "01065689787");
        var 유저1 = 신규유저_생성_요청_후_토큰추출("유저1", "01065689737");
        var 유저2 = 신규유저_생성_요청_후_토큰추출("유저2", "01065681414");

        var dailyFalling = 그날의주제어_생성_요청();

        //when
    }
}
