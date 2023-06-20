package com.tht.api.app.repository.mapper;

import com.querydsl.core.annotations.QueryProjection;
import java.time.LocalDate;

public record ChatRoomMapper(
    long chatRoomIdx,
    String talkSubject,
    String talkIssue,
    String startDate
) {

    @QueryProjection
    public ChatRoomMapper{}
}
