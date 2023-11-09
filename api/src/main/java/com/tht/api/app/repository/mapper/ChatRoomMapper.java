package com.tht.api.app.repository.mapper;

import com.querydsl.core.annotations.QueryProjection;
import java.time.LocalDateTime;

public record ChatRoomMapper(
    long chatRoomIdx,
    String talkSubject,
    String talkIssue,
    LocalDateTime startDate
) {

    @QueryProjection
    public ChatRoomMapper{}
}
