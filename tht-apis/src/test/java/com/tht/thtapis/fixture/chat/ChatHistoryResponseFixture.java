package com.tht.thtapis.fixture.chat;

import com.tht.thtapis.facade.chat.response.ChatResponse;

import java.time.LocalDateTime;
import java.util.List;

public class ChatHistoryResponseFixture {

    private static final String chatIdx = "1314objectIDa12134";
    private static final String sender = "짱구";
    private static final String senderUuid = "user-uuid";
    private static final String msg = "채팅 내용";
    private static final String imgUrl = "img-url";
    private static final LocalDateTime dateTime = LocalDateTime.of(2023, 11, 13, 17, 30, 12, 1234);

    public static ChatResponse make() {

        return new ChatResponse(chatIdx, sender, senderUuid, msg, imgUrl, dateTime);
    }

    public static List<ChatResponse> makeList() {
        return List.of(make());
    }

    public static String getChatIdx() {
        return chatIdx;
    }

}
