package com.tht.thtapis.acceptance;

import static com.tht.thtapis.acceptance.ChatRoomAcceptanceStep.채팅방_나가기_요청;
import static com.tht.thtapis.acceptance.ChatRoomAcceptanceStep.채팅방_리스트_조회_요청;
import static com.tht.thtapis.acceptance.ChatRoomAcceptanceStep.채팅방_상세조회_요청;
import static com.tht.thtapis.acceptance.LikeAcceptanceStep.좋아요_요청;
import static com.tht.thtapis.acceptance.UserAcceptanceStep.신규유저_생성_요청_후_토큰추출;
import static com.tht.thtapis.acceptance.UserAcceptanceStep.유저_차단_요청;
import static com.tht.thtapis.acceptance.UserAcceptanceStep.유저계정_탈퇴_요청;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import com.tht.infra.dailyfalling.DailyFalling;
import com.tht.thtapis.acceptance.config.mongo.AcceptanceTestWithMongo;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
    @DisplayName("채팅방 리스트 조회 인수테스트")
    @Test
    void getChatRooms() {

        Map<String, String> tokenMap = new HashMap<>();
        Map<String, Long> chatRoomMap = new HashMap<>();
        String otherUser = "채팅할상대방";

        //given
        String 로그인유저토큰 = 신규유저_생성_요청_후_토큰추출("박형싴케이", "0105513171");
        DailyFalling dailyFalling = 그날의주제어_생성_요청();

        for (int i = 1; i <= 9; i++) {
            String otherAccessToken = 신규유저_생성_요청_후_토큰추출(otherUser + i, "0105513123" + i);
            좋아요_요청(로그인유저토큰, getUserUuid(otherAccessToken), dailyFalling.getIdx());

            var 좋아요_요청_결과 = 좋아요_요청(otherAccessToken, getUserUuid(로그인유저토큰),
                dailyFalling.getIdx());

            tokenMap.put(otherUser + i, otherAccessToken);
            chatRoomMap.put(otherUser + i, 좋아요_요청_결과.jsonPath().getLong("chatRoomIdx"));
        }

        //scenario 1 - 내가 나간 채팅방
        채팅방_나가기_요청(로그인유저토큰, chatRoomMap.get(otherUser + 1));

        //scenario 2 - 내가 차단한 상대방 채팅방
        유저_차단_요청(로그인유저토큰, getUserUuid(tokenMap.get(otherUser + 2)));

        //scenario 3 - 상대방 유저가 나를 차단
        유저_차단_요청(tokenMap.get(otherUser + 3), getUserUuid(로그인유저토큰));

        //scenario 4 - 상대방 유저가 나와의 채팅방을 나감
        채팅방_나가기_요청(tokenMap.get(otherUser + 4), chatRoomMap.get(otherUser + 4));

        //scenario 5 - 상대방 유저가 탈퇴
        유저계정_탈퇴_요청(tokenMap.get(otherUser + 5));

        //when
        var response = 채팅방_리스트_조회_요청(로그인유저토큰);

        //then
        List<Objects> unChatAbleRoom = response.jsonPath()
            .getList("findAll{it.partnerName in ['채팅할상대방3', '채팅할상대방4', '채팅할상대방5']}");

        assertAll(
            () -> assertThat(response.jsonPath().getList("")).hasSize(7),
            () -> assertThat(response.jsonPath().getList("partnerName"))
                .isNotIn(otherUser + 1, otherUser + 2),
            () -> assertThat(unChatAbleRoom).hasSize(3),
            () -> assertThat(unChatAbleRoom).extracting("currentMessage")
                .isEqualTo(
                    List.of("현재 메세지를 보낼 수 없습니다.", "현재 메세지를 보낼 수 없습니다.", "현재 메세지를 보낼 수 없습니다.")
                ),
            () -> assertThat(unChatAbleRoom).extracting("isAvailableChat")
                .isEqualTo(List.of(false, false, false)),
            () -> assertThat(response.jsonPath()
                .getString("find{it.partnerName == '채팅할상대방7'}.currentMessage"))
                .isEqualTo("매칭된 무디와 먼저 대화를 시작해 보세요."),
            () -> assertThat(response.jsonPath()
                .getBoolean("find{it.partnerName == '채팅할상대방7'}.isAvailableChat")).isTrue()
        );

    }


    /**
     * scenario
     * <br> - 로그인한 유저가 속한 채팅방을 조회한다. (입장)
     */
    @DisplayName("채팅방 상세 조회 인수테스트 - 채팅 가능 케이스")
    @Test
    void getChatRoomDetail() {

        //given
        String 로그인유저토큰 = 신규유저_생성_요청_후_토큰추출("박형싴케이", "0105513171");
        DailyFalling dailyFalling = 그날의주제어_생성_요청();

        String otherAccessToken = 신규유저_생성_요청_후_토큰추출("유저", "01055131232" );
        좋아요_요청(로그인유저토큰, getUserUuid(otherAccessToken), dailyFalling.getIdx());

        var 좋아요_요청_결과 = 좋아요_요청(otherAccessToken, getUserUuid(로그인유저토큰),
            dailyFalling.getIdx());

        //when
        var response = 채팅방_상세조회_요청(로그인유저토큰, 좋아요_요청_결과.jsonPath().getLong("chatRoomIdx"));

        //then
        assertThat(response.jsonPath().getBoolean("isChatAble")).isTrue();

    }

    /**
     * scenario
     * <br> - 로그인한 유저가 속한 채팅방을 조회한다. (입장)
     * <br> - 상대방 유저가 해당 유저를 차단하면 채팅이 불가 상태입니다.
     * <br> - 상대방 유저가 로그인한 유저의 채팅방을 나가면 채팅 불가 상태입니다.
     * <br> - 상대방 유저가 탈퇴했다면 채팅 불가 상태입니다.
     */
    @DisplayName("채팅방 상세 조회 인수테스트 - 채팅 불가능 케이스")
    @Test
    void getChatRoomDetailAtNotChatAble() {

        Map<String, String> tokenMap = new HashMap<>();
        Map<String, Long> chatRoomMap = new HashMap<>();
        String otherUser = "채팅할상대방";

        //given
        String 로그인유저토큰 = 신규유저_생성_요청_후_토큰추출("박형싴케이", "0105513171");
        DailyFalling dailyFalling = 그날의주제어_생성_요청();

        for (int i = 1; i <= 3; i++) {
            String otherAccessToken = 신규유저_생성_요청_후_토큰추출(otherUser + i, "0105513123" + i);
            좋아요_요청(로그인유저토큰, getUserUuid(otherAccessToken), dailyFalling.getIdx());

            var 좋아요_요청_결과 = 좋아요_요청(otherAccessToken, getUserUuid(로그인유저토큰),
                dailyFalling.getIdx());

            tokenMap.put(otherUser + i, otherAccessToken);
            chatRoomMap.put(otherUser + i, 좋아요_요청_결과.jsonPath().getLong("chatRoomIdx"));
        }


        //scenario 1 - 상대방 유저가 나를 차단
        유저_차단_요청(tokenMap.get(otherUser +1), getUserUuid(로그인유저토큰));

        //scenario 2 - 상대방 유저가 나와의 채팅방을 나감
        채팅방_나가기_요청(tokenMap.get(otherUser + 2), chatRoomMap.get(otherUser + 2));

        //scenario 3 - 상대방 유저가 탈퇴
        유저계정_탈퇴_요청(tokenMap.get(otherUser + 3));

        //when
        var response1 = 채팅방_상세조회_요청(로그인유저토큰, chatRoomMap.get(otherUser+1));
        var response2 = 채팅방_상세조회_요청(로그인유저토큰, chatRoomMap.get(otherUser+2));
        var response3 = 채팅방_상세조회_요청(로그인유저토큰, chatRoomMap.get(otherUser+3));

        //then
        assertAll(
            () -> assertThat(response1.jsonPath().getBoolean("isChatAble")).isFalse(),
            () -> assertThat(response2.jsonPath().getBoolean("isChatAble")).isFalse(),
            () -> assertThat(response3.jsonPath().getBoolean("isChatAble")).isFalse()
        );

    }
}
