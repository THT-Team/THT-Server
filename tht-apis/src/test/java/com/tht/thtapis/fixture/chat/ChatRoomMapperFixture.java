package com.tht.thtapis.fixture.chat;

import com.tht.infra.EntityState;
import com.tht.infra.chat.mapper.ChatRoomMapper;

import java.time.LocalDateTime;

public class ChatRoomMapperFixture {

    private static final long chatRoomIdx = 1;
    private static final String talkSubject = "마음";
    private static final String talkIssue = "너의 마음은 어떠니?";
    private static final LocalDateTime startDate = LocalDateTime.now();
    private static final EntityState isOutPartner = EntityState.ACTIVE;
    private static final EntityState isWithDrawPartner = EntityState.ACTIVE;

    public static ChatRoomMapper make() {
        return new ChatRoomMapper(chatRoomIdx, talkSubject, talkIssue, startDate, isOutPartner,
            isWithDrawPartner);
    }

}
