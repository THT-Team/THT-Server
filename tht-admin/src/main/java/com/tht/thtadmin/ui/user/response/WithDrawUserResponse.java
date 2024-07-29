package com.tht.thtadmin.ui.user.response;

import com.tht.enums.EntityState;

public record WithDrawUserResponse(
    String userUuid,
    String username,
    String reason,
    String feedBack,
    EntityState userStatus,
    String requestDate
) {
}
