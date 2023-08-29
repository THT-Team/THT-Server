package com.tht.api.app.acceptance;

import static com.tht.api.app.acceptance.LikeAcceptanceStep.좋아요_요청;
import static com.tht.api.app.acceptance.UserAcceptanceStep.신규유저_생성_요청_후_토큰추출;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import com.tht.api.app.acceptance.config.AcceptanceTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LikeAcceptanceTest extends AcceptanceTest {

    @DisplayName("내가 좋아요누른 상대망이 나를 좋아할 때 채팅방 생성")
    @Test
    void togetherLike() {

        //given
        var 나 = 신규유저_생성_요청_후_토큰추출("일반 사용자1", "01065689787");
        var 내가_좋아하는_사람 = 신규유저_생성_요청_후_토큰추출("일반 사용자2", "01065689737");

        //given
        var dailyFalling = 그날의주제어_생성_요청();
        좋아요_요청(내가_좋아하는_사람, getUserUuid(나), dailyFalling.getIdx());

        //when
        var response = 좋아요_요청(나, getUserUuid(내가_좋아하는_사람), dailyFalling.getIdx());

        //then
        assertAll(
            () -> assertThat(response.statusCode()).isEqualTo(201),
            () -> assertThat(response.jsonPath().getBoolean("isMatching")).isTrue(),
            () -> assertThat(response.jsonPath().getLong("chatRoomIdx")).isNotNull()
        );
    }

    @DisplayName("나만 좋아요를 누를 때, 채팅방 생성되지 않음")
    @Test
    void onlyMineLike() {

        //given
        var 나 = 신규유저_생성_요청_후_토큰추출("일반 사용자1", "01065689787");
        var 내가_좋아하는_사람 = 신규유저_생성_요청_후_토큰추출("일반 사용자2", "01065689737");

        //given
        var dailyFalling = 그날의주제어_생성_요청();

        //when
        var response = 좋아요_요청(나, getUserUuid(내가_좋아하는_사람), dailyFalling.getIdx());

        //then
        assertAll(
            () -> assertThat(response.statusCode()).isEqualTo(201),
            () -> assertThat(response.jsonPath().getBoolean("isMatching")).isFalse(),
            () -> assertThat(response.jsonPath().getLong("chatRoomIdx")).isNull()
        );
    }
}