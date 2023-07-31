package com.tht.api.app.repository.mapper;

import com.querydsl.core.annotations.QueryProjection;

public record InterestTestMapper(
        Integer idx,
        String name
) {
    @QueryProjection
    public InterestTestMapper{}
}
