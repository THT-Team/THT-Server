package com.tht.api.app.facade.chat.response;

import com.tht.api.app.repository.mapper.ChatRoomMapper;

public record ChatRoomResponse(
    long chatRoomIdx,
    String talkSubject,
    String talkIssue,
    String startDate
) {

    public static ChatRoomResponse of(final ChatRoomMapper mapper) {
        return new ChatRoomResponse(
            mapper.chatRoomIdx(),
            mapper.talkSubject(),
            mapper.talkIssue(),
            mapper.startDate()
        );
    }
}
