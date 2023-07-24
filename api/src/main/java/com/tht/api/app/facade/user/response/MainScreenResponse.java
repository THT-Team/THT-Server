package com.tht.api.app.facade.user.response;

import com.tht.api.app.config.utils.UnixTimeUtils;
import java.time.LocalDateTime;
import java.util.List;

public record MainScreenResponse(
    long selectDailyFallingIdx,
    long topicExpirationUnixTime,
    List<MainScreenUserInfoResponse> userInfos
) {

    public static MainScreenResponse of(final long dailyFallingIdx,
        final LocalDateTime topicExpirationUnixTime, final List<MainScreenUserInfoResponse> responses) {

        return new MainScreenResponse(
            dailyFallingIdx,
            UnixTimeUtils.convertUnixTimeForUTC(topicExpirationUnixTime),
            responses
        );
    }

    public static MainScreenResponse empty() {
        return new MainScreenResponse(-1, 0, List.of());
    }

}