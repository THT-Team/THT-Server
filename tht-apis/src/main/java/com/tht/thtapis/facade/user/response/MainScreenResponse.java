package com.tht.thtapis.facade.user.response;

import com.tht.thtcommonutils.utils.UnixTimeUtils;

import java.time.LocalDateTime;
import java.util.List;

public record MainScreenResponse(
    long selectDailyFallingIdx,
    long topicExpirationUnixTime,
    boolean isLast,
    boolean isUserEmpty,
    List<MainScreenUserInfoResponse> userInfos
) {

    public static MainScreenResponse of(final long dailyFallingIdx,
                                        final LocalDateTime topicExpirationUnixTime,
                                        final List<MainScreenUserInfoResponse> responses, int size) {

        return new MainScreenResponse(
            dailyFallingIdx,
            UnixTimeUtils.convertUnixTimeForUTC(topicExpirationUnixTime),
            responses.size() != size,
            false,
            responses
        );
    }

    public static MainScreenResponse empty() {
        return new MainScreenResponse(-1, 0, true, false, java.util.List.of());
    }

    public static MainScreenResponse choiceUsersEmpty(
        final long dailyFallingIdx,
        final LocalDateTime topicExpirationUnixTime
    ) {

        return new MainScreenResponse(
            dailyFallingIdx,
            UnixTimeUtils.convertUnixTimeForUTC(topicExpirationUnixTime),
            true,
            true,
            java.util.List.of()
        );
    }

}
