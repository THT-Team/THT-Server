package com.tht.thtapis.facade.main.response;


import com.tht.infra.dailyfalling.DailyFallingType;
import com.tht.thtcommonutils.utils.UnixTimeUtils;

import java.time.LocalDateTime;
import java.util.List;

public record DailyFallingResponse(
        long expirationUnixTime,
        String type,
        String introduction,
        List<DailyFallingTopicResponse> fallingTopicList
) {

    public static DailyFallingResponse empty() {
        String empty = "";
        return new DailyFallingResponse(-1, empty, empty, List.of());
    }

    public static DailyFallingResponse of(final LocalDateTime endDateTime, final DailyFallingType type,
                                          final String introduction, final List<DailyFallingTopicResponse> topicResponses) {

        return new DailyFallingResponse(
                UnixTimeUtils.convertUnixTimeForUTC(endDateTime),
                type.getValue(),
                introduction,
                topicResponses
        );
    }
}
