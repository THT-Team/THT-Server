package com.tht.thtapis.facade.chat.response;

import com.tht.domain.entity.chat.mapper.ChatRoomMapper;
import com.tht.domain.entity.chat.mapper.ChatRoomUserMapper;
import java.time.Instant;
import java.util.List;

public record ChatRoomResponse(
    long chatRoomIdx,
    String talkSubject,
    String talkIssue,
    Instant startDate,
    boolean isChatAble,
    List<Participant> participants
) {

    public static ChatRoomResponse of(final ChatRoomMapper mapper, final boolean isChatAble,
        List<ChatRoomUserMapper> allParticipator) {
        return new ChatRoomResponse(
            mapper.chatRoomIdx(),
            mapper.talkSubject(),
            mapper.talkIssue(),
            mapper.startDate(),
            isChatAble,
            allParticipator.stream().map(Participant::of).toList()
        );
    }
}

record Participant(
    String userUuid,
    String userName,
    String profileUrl
) {

    public static Participant of(final ChatRoomUserMapper user) {
        return new Participant(user.userUuid(), user.userName(), user.profileUrl());
    }
}
