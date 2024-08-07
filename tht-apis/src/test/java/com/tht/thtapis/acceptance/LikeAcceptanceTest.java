package com.tht.thtapis.acceptance;

import static com.tht.thtapis.acceptance.LikeAcceptanceStep.좋아요_거절_요청;
import static com.tht.thtapis.acceptance.LikeAcceptanceStep.좋아요_리스트_조회_요청;
import static com.tht.thtapis.acceptance.LikeAcceptanceStep.좋아요_요청;
import static com.tht.thtapis.acceptance.UserSignUpAcceptanceStep.신규유저_생성_요청_후_토큰추출;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;

import com.tht.thtapis.acceptance.config.AcceptanceTest;
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
            () -> assertThat(response.jsonPath().getObject("chatRoomIdx", Long.class)).isNotNull()
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
            () -> assertThat(response.jsonPath().getObject("chatRoomIdx", Long.class)).isNull()
        );
    }

    /**
     * feature : 좋아요 리스트 조회<br> scenario<br> 1. 받은 좋아요 리스트를 데일리 토픽 idx 역순으로 정렬<br> 2. 같은 토픽이면 최근 받은
     * 좋아요 순으로 정렬<br> 3. 대화가 시작되었거나, 거부당한 유저는 리스트에서 제거<br>
     */
    @DisplayName("내가 받은 좋아요 리스트 확인")
    @Test
    void getLikes() {

        //given
        var 나 = 신규유저_생성_요청_후_토큰추출("일반 사용자1", "01065689787");
        var 나를_좋아하는_사람 = 신규유저_생성_요청_후_토큰추출("일반 사용자2", "01065689737");
        var 서로_좋아하는_사람 = 신규유저_생성_요청_후_토큰추출("일반 사용자3", "01065829737");
        var 내가_거절할_사람 = 신규유저_생성_요청_후_토큰추출("거절할사람", "01065829717");

        var dailyFalling = 그날의주제어_생성_요청();

        좋아요_요청(나를_좋아하는_사람, getUserUuid(나), dailyFalling.getIdx());
        좋아요_요청(서로_좋아하는_사람, getUserUuid(나), dailyFalling.getIdx());
        좋아요_요청(나, getUserUuid(서로_좋아하는_사람), dailyFalling.getIdx());
        좋아요_요청(내가_거절할_사람, getUserUuid(나), dailyFalling.getIdx());

        //when
        var response = 좋아요_리스트_조회_요청(나);

        //then
        assertAll(
            () -> assertThat(response.statusCode()).isEqualTo(200),
            () -> assertThat(response.jsonPath().getList("likeList.username")).containsExactly(
                "거절할사람", "일반 사용자2")
        );

        //when
        List<Integer> likeIdx = response.jsonPath()
            .getList("likeList.findAll{ likeList -> likeList.username == '거절할사람'}.likeIdx");

        좋아요_거절_요청(나, likeIdx.get(0));

        var afterRejectResponse = 좋아요_리스트_조회_요청(나);

        assertAll(
            () -> assertThat(afterRejectResponse.statusCode()).isEqualTo(200),
            () -> assertThat(afterRejectResponse.jsonPath().getList("likeList.username")).containsExactly( "일반 사용자2")
        );
    }

}