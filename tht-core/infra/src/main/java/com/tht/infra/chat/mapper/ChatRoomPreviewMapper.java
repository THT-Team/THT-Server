package com.tht.infra.chat.mapper;

import com.querydsl.core.annotations.QueryProjection;
import com.tht.enums.EntityState;

import java.time.LocalDateTime;

public record ChatRoomPreviewMapper(
    Long chatRoomIdx,
    String partnerUuid,
    String partnerName,
    String partnerProfileUrl,
    LocalDateTime chatRoomCreatedAt,
    EntityState state,
    EntityState userState

) {

    @QueryProjection
    public ChatRoomPreviewMapper{}

    public boolean isChatOutPartner(){
        return !EntityState.ACTIVE.equals(state) || !EntityState.ACTIVE.equals(userState);
    }
}
