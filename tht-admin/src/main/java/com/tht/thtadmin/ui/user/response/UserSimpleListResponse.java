package com.tht.thtadmin.ui.user.response;

import com.tht.enums.EntityState;
import com.tht.thtcommonutils.utils.CustomDateFormatUtils;

import java.time.LocalDateTime;

public record UserSimpleListResponse(
    String username,
    String createdAt,
    EntityState userSate
) {

    public static UserSimpleListResponse of(final String username, final LocalDateTime createdAt, final EntityState userSate) {

        final String convertCreateAt = createdAt.format(CustomDateFormatUtils.getDateTimeInstance());
        return new UserSimpleListResponse(username, convertCreateAt, userSate);
    }
}
