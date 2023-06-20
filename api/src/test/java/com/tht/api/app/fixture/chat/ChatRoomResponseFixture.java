package com.tht.api.app.fixture.chat;

import com.tht.api.app.facade.chat.response.ChatRoomPreviewResponse;
import com.tht.api.app.facade.chat.response.ChatRoomResponse;
import java.time.LocalDate;
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

    public static ChatRoomPreviewResponse makePreviewResponse() {
        return new ChatRoomPreviewResponse(chatRoomIdx, partnerName, partnerProfileUrl, currentMessage,
            messageTime);
    }

    public static List<ChatRoomPreviewResponse> makePreviewResponseList() {
        return List.of(makePreviewResponse());
    }

    public static ChatRoomResponse make() {
        return new ChatRoomResponse(chatRoomIdx,talkSubject, talkIssue, startDate);
    }
}
