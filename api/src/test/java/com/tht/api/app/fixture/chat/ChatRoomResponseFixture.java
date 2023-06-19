package com.tht.api.app.fixture.chat;

import com.tht.api.app.facade.chat.response.ChatRoomPreviewResponse;
import java.time.LocalDateTime;
import java.util.List;

public class ChatRoomResponseFixture {

    private static final int chatRoomIdx = 1;
    private static final String partnerName = "채팅상대방이름";
    private static final String partnerProfileUrl = "상대방프로필 url";
    private static final String currentMessage = "최근 메세지";
    private static final LocalDateTime messageTime = LocalDateTime.of(2023, 3, 21, 12, 13, 4, 1);


    public static ChatRoomPreviewResponse make() {
        return new ChatRoomPreviewResponse(chatRoomIdx, partnerName, partnerProfileUrl, currentMessage,
            messageTime);
    }

    public static List<ChatRoomPreviewResponse> makeList() {
        return List.of(make());
    }
}
