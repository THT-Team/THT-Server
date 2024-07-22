package com.tht.thtapis.facade.chat.response;

import com.tht.domain.entity.chat.mapper.ChatRoomMapper;

import java.time.format.DateTimeFormatter;

public record ChatRoomResponse(
    long chatRoomIdx,
    String talkSubject,
    String talkIssue,
    String startDate,
    boolean isChatAble
) {

    public static ChatRoomResponse of(final ChatRoomMapper mapper, final boolean isChatAble) {
        return new ChatRoomResponse(
            mapper.chatRoomIdx(),
            mapper.talkSubject(),
            mapper.talkIssue(),
            mapper.startDate().format(DateTimeFormatter.ofPattern("y년 M월 d일")),
            isChatAble
        );
    }
}
