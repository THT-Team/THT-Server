package com.tht.domain.entity.user.service.dto;

import com.tht.domain.entity.user.repository.querydsl.mapper.UserWithDrawLogMapper;
import com.tht.enums.EntityState;
import java.time.Instant;

public record WithDrawUserDto(
    String userUuid,
    String username,
    String reason,
    String feedBack,
    EntityState userStatus,
    Instant requestDate
) {

    public static WithDrawUserDto ofMapper(final UserWithDrawLogMapper mapper) {
        return new WithDrawUserDto(
            mapper.userUuid(),
            mapper.username(),
            mapper.reason(),
            mapper.feedBack(),
            mapper.userStatus(),
            mapper.requestDate()
        );
    }
}
