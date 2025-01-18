package com.tht.thtapis.fixture.chat;

import com.tht.domain.entity.chat.mapper.ChatRoomMapper;
import com.tht.domain.entity.chat.mapper.ChatRoomUserMapper;
import com.tht.enums.EntityState;
import com.tht.thtapis.facade.chat.response.ChatRoomPreviewResponse;
import com.tht.thtapis.facade.chat.response.ChatRoomResponse;

import java.time.LocalDateTime;
import java.util.List;

public class ChatRoomResponseFixture {

    private static final int chatRoomIdx = 1;
    private static final String partnerName = "채팅상대방이름";
    private static final String partnerProfileUrl = "상대방프로필 url";
    private static final String currentMessage = "최근 메세지";
    private static final LocalDateTime messageTime = LocalDateTime.of(2023, 3, 21, 12, 13, 4, 1);

    private static final String talkSubject = "마음";
    private static final String talkIssue = "너의 마음은 어떠니?";
    private static final String startDate = "2023년 3월 21일";
    private static final boolean isChatAble = true;

    public static ChatRoomPreviewResponse makePreviewResponse() {
        return new ChatRoomPreviewResponse(chatRoomIdx, partnerName, partnerProfileUrl, currentMessage,
            messageTime, isChatAble);
    }

    public static List<ChatRoomPreviewResponse> makePreviewResponseList() {
        return List.of(makePreviewResponse());
    }

    public static ChatRoomResponse make() {
        List<ChatRoomUserMapper> users = List.of(
            new ChatRoomUserMapper("1번-user-uuid", "1번 참가자 이름", "1번 참가자 대표 사진"),
            new ChatRoomUserMapper("2번-user-uuid", "2번 참가자 이름", "2번 참가자 대표 사진")
        );

        ChatRoomMapper chatRoomMapper = new ChatRoomMapper(chatRoomIdx, talkSubject, talkIssue, LocalDateTime.now(), EntityState.ACTIVE, EntityState.ACTIVE);

        return ChatRoomResponse.of(chatRoomMapper, isChatAble, users);
    }
}
