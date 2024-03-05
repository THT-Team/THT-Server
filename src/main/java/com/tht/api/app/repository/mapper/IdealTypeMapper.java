package com.tht.api.app.repository.mapper;

import com.querydsl.core.annotations.QueryProjection;

public record IdealTypeMapper(
    Integer idx,
    String name,
    String emojiCode
) {

    @QueryProjection
    public IdealTypeMapper{}
}
