package com.tht.infra.chat.mapper;

import com.querydsl.core.annotations.QueryProjection;
import com.tht.infra.EntityState;

import java.time.LocalDateTime;

public record ChatRoomMapper(
    long chatRoomIdx,
    String talkSubject,
    String talkIssue,
    LocalDateTime startDate,
    EntityState chatRoomUserState,
    EntityState partnerUserState

) {

    @QueryProjection
    public ChatRoomMapper{}

    public boolean isActiveUser() {
        return partnerUserState.equals(EntityState.ACTIVE);
    }

    public boolean isNonOutRoomUser() {
        return chatRoomUserState.equals(EntityState.ACTIVE);
    }
}
