package com.tht.api.app.facade.main.response;

import com.tht.api.app.config.utils.UnixTimeUtils;
import java.time.LocalDateTime;
import java.util.List;

public record DailyFallingResponse(
    long expirationUnixTime,
    List<DailyFallingTopicResponse> fallingTopicList
) {

    public static DailyFallingResponse empty() {
        return new DailyFallingResponse(-1, List.of());
    }

    public static DailyFallingResponse of(final LocalDateTime endDateTime,
        final List<DailyFallingTopicResponse> topicResponses) {

        return new DailyFallingResponse(UnixTimeUtils.convertUnixTimeForUTC(endDateTime),
            topicResponses);
    }
}
