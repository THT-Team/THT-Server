package com.tht.api.app.facade.user.request;

import jakarta.validation.constraints.NotNull;
import java.util.List;

public record MainScreenUserInfoRequest(

    @NotNull(message = "alreadySeenUserUuidList 가 null 입니다.") List<String> alreadySeenUserUuidList,
    Long userDailyFallingCourserIdx,
    Integer size
) {

}
