package com.tht.api.app.repository.mapper;

import com.querydsl.core.annotations.QueryProjection;
import java.time.LocalDateTime;

public record ChatRoomPreviewMapper(
    Long chatRoomIdx,
    String partnerUuid,
    String partnerName,
    String partnerProfileUrl,
    LocalDateTime chatRoomCreatedAt

) {

    @QueryProjection
    public ChatRoomPreviewMapper{}
}
