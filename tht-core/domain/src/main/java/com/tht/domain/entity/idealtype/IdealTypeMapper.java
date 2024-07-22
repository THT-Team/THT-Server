package com.tht.domain.entity.idealtype;

import com.querydsl.core.annotations.QueryProjection;

public record IdealTypeMapper(
    Integer idx,
    String name,
    String emojiCode
) {

    @QueryProjection
    public IdealTypeMapper{}
}
