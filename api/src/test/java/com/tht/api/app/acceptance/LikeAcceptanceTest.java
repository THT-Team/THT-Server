package com.tht.api.app.acceptance;

import static com.tht.api.app.acceptance.LikeAcceptanceStep.좋아요_요청;
import static com.tht.api.app.acceptance.UserAcceptanceStep.신규유저_생성_요청_후_토큰추출;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import com.tht.api.app.acceptance.config.AcceptanceTest;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
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
     * feature : 좋아요 리스트 조회<br>
     * scenario<br>
     * 1. 받은 좋아요 리스트를 데일리 토픽 idx 역순으로 정렬<br>
     * 2. 같은 토픽이면 최근 받은 좋아요 순으로 정렬<br>
     * 3. 대화가 시작되었거나, 거부당한 유저는 리스트에서 제거<br>
     */
    @DisplayName("내가 받은 좋아요 리스트 확인")
    @Test
    void getLikes() {

        //given
        var 나 = 신규유저_생성_요청_후_토큰추출("일반 사용자1", "01065689787");
        var 나를_좋아하는_사람 = 신규유저_생성_요청_후_토큰추출("일반 사용자2", "01065689737");
        var 서로_좋아하는_사람 = 신규유저_생성_요청_후_토큰추출("일반 사용자3", "01065829737");

        var dailyFalling = 그날의주제어_생성_요청();

        좋아요_요청(나를_좋아하는_사람, getUserUuid(나), dailyFalling.getIdx());
        좋아요_요청(서로_좋아하는_사람, getUserUuid(나), dailyFalling.getIdx());
        좋아요_요청(나, getUserUuid(서로_좋아하는_사람), dailyFalling.getIdx());

        //when
        var response = 좋아요_리스트_조회_요청(나);

        //then
        assertAll(
            () -> assertThat(response.statusCode()).isEqualTo(200),
            () -> assertThat(response.jsonPath().getList("username")).containsExactly("일반 사용자2")
        );
    }

    private static ExtractableResponse<Response> 좋아요_리스트_조회_요청(String accessToken) {
        return RestAssured.given().log().all()
            .auth().oauth2(accessToken)
            .when().get("/like/receives")
            .then().log().all()
            .extract();
    }
}