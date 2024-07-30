package com.tht.domain.entity.block.dto;

import com.tht.domain.entity.block.repository.mapper.UserBlockMapper;
import com.tht.enums.EntityState;
import com.tht.enums.user.Gender;
import com.tht.thtcommonutils.utils.CustomDateFormatUtils;

public record  UserBlockDto(
    String userUuid,
    String username,
    Gender gender,
    EntityState userStatus,
    long blockCount,
    String currentBlockDate
) {
    public static UserBlockDto ofMapper(UserBlockMapper mapper) {
        return new UserBlockDto(
            mapper.userUuid(),
            mapper.username(),
            mapper.gender(),
            mapper.userStatus(),
            mapper.blockCount(),
            mapper.currentBlockDate().format(CustomDateFormatUtils.getDateTimeInstance())
        );
    }
}
