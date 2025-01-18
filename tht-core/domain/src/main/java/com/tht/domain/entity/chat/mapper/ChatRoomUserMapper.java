package com.tht.domain.entity.chat.mapper;

import com.querydsl.core.annotations.QueryProjection;

public record ChatRoomUserMapper(
    String userUuid,
    String userName,
    String profileUrl
) {

    @QueryProjection
    public ChatRoomUserMapper {}
}
