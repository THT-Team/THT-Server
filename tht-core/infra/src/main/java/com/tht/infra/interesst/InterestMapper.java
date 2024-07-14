package com.tht.infra.interesst;

import com.querydsl.core.annotations.QueryProjection;

public record InterestMapper(
    Integer idx,
    String name,
    String emojiCode
) {

    @QueryProjection
    public InterestMapper{}
}
