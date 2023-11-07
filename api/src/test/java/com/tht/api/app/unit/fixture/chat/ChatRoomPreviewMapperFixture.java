package com.tht.api.app.unit.fixture.chat;

import com.tht.api.app.entity.enums.EntityState;
import com.tht.api.app.repository.mapper.ChatRoomPreviewMapper;
import java.time.LocalDateTime;

public class ChatRoomPreviewMapperFixture {

    private final static Long chatRoomIdx = 1L;
    private final static String partnerUuid = "uuid";
    private final static String partnerName = "상대방 이름";
    private final static String partnerProfileUrl = "profile_url";
    private final static LocalDateTime chatRoomCreatedAt = LocalDateTime.now();
    private final static EntityState entityState = EntityState.ACTIVE;

    public static ChatRoomPreviewMapper make(final Long roomIdx) {

        return new ChatRoomPreviewMapper(roomIdx, partnerUuid, partnerName, partnerProfileUrl, chatRoomCreatedAt, entityState);
    }

    public static ChatRoomPreviewMapper make() {

        return new ChatRoomPreviewMapper(chatRoomIdx, partnerUuid, partnerName, partnerProfileUrl, chatRoomCreatedAt, entityState);
    }
}
