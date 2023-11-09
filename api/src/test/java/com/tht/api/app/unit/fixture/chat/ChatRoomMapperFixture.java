package com.tht.api.app.unit.fixture.chat;

import com.tht.api.app.repository.mapper.ChatRoomMapper;
import java.time.LocalDateTime;

public class ChatRoomMapperFixture {

    private static final long chatRoomIdx = 1;
    private static final String talkSubject = "마음";
    private static final String talkIssue = "너의 마음은 어떠니?";
    private static final LocalDateTime startDate = LocalDateTime.now();

    public static ChatRoomMapper make() {
        return new ChatRoomMapper(chatRoomIdx, talkSubject, talkIssue, startDate);
    }

}
