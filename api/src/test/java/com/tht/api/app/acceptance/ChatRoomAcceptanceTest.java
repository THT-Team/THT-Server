package com.tht.api.app.acceptance;

import static com.tht.api.app.acceptance.ChatRoomAcceptanceStep.채팅방_리스트_조회_요청;
import static com.tht.api.app.acceptance.LikeAcceptanceStep.좋아요_요청;
import static com.tht.api.app.acceptance.UserAcceptanceStep.신규유저_생성_요청_후_토큰추출;
import static com.tht.api.app.acceptance.UserAcceptanceStep.유저_차단_요청;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import com.tht.api.app.acceptance.config.mongo.AcceptanceTestWithMongo;
import com.tht.api.app.entity.meta.DailyFalling;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

class ChatRoomAcceptanceTest extends AcceptanceTestWithMongo {

    /**
     * scenario
     * <br> - 로그인한 유저가 속한 채팅방 리스트를 조회한다.
     * <br> - 로그인한 유저가 나간 방은 조회되지 않습니다.
     * <br> - 로그인한 유저가 차단한 상대방 유저의 채팅방은 보여지지 않습니다.
     * <br> - 상대방 유저가 해당 유저를 차단하면 채팅이 불가 상태입니다.
     * <br> - 상대방 유저가 로그인한 유저의 채팅방을 나가면 채팅 불가 상태입니다.
     * <br> - 상대방 유저가 탈퇴했다면 채팅 불가 상태입니다.
     */
    @DisplayName("채팅방 리스트 조회")
    @Test
    void getChatRooms() {

        Map<String, String> tokenMap = new HashMap<>();
        Map<String, Long> chatRoomMap = new HashMap<>();

        //given
        String 로그인유저토큰 = 신규유저_생성_요청_후_토큰추출("박형싴케이", "0105513171");
        DailyFalling dailyFalling = 그날의주제어_생성_요청();

        for (int i = 1; i <= 9; i++) {
            String otherAccessToken = 신규유저_생성_요청_후_토큰추출("채팅할상대방" + i, "0105513123" + i);
            좋아요_요청(로그인유저토큰, getUserUuid(otherAccessToken), dailyFalling.getIdx());
            var 좋아요_요청_결과 = 좋아요_요청(otherAccessToken, getUserUuid(로그인유저토큰),
                dailyFalling.getIdx());

            tokenMap.put("채팅할상대방" + i, otherAccessToken);
            chatRoomMap.put("채팅할상대방" + i, 좋아요_요청_결과.jsonPath().getLong("chatRoomIdx"));
        }


        //scenario 1 - 내가 나간 채팅방
        채팅방_나가기_요청(로그인유저토큰, chatRoomMap.get("채팅할상대방1"));

        //scenario 2 - 내가 차단한 상대방 채팅방
        유저_차단_요청(로그인유저토큰, getUserUuid(tokenMap.get("채팅할상대방2")));



        //when
        var response = 채팅방_리스트_조회_요청(로그인유저토큰);

        //then
        assertAll(
            () -> assertThat(response.jsonPath().getList("")).hasSize(7),
            () -> assertThat(response.jsonPath().getList("partnerName")).isNotIn("채팅할상대방1", "채팅할상대방2")
        );
    }

    private ExtractableResponse<Response> 채팅방_나가기_요청(String accessToken, long chatRoomIdx) {

        return RestAssured.given().log().all()
            .auth().oauth2(accessToken)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .when()
            .post("/chat/out/room/{chat-room-idx}", chatRoomIdx)
            .then().log().all()
            .extract();
    }

}
