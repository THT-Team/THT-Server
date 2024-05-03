package com.tht.api.app.facade.main.response;

import com.tht.api.app.config.utils.UnixTimeUtils;
import com.tht.api.app.entity.enums.DailyFallingType;

import java.time.LocalDateTime;
import java.util.List;

public record DailyFallingResponse(
        long expirationUnixTime,
        String type,
        List<DailyFallingTopicResponse> fallingTopicList
) {

    public static DailyFallingResponse empty() {
        String empty = "";
        return new DailyFallingResponse(-1, empty, List.of());
    }

    public static DailyFallingResponse of(final LocalDateTime endDateTime, final DailyFallingType type,
                                          final List<DailyFallingTopicResponse> topicResponses) {

        return new DailyFallingResponse(
                UnixTimeUtils.convertUnixTimeForUTC(endDateTime),
                type.getValue(),
                topicResponses
        );
    }
}
