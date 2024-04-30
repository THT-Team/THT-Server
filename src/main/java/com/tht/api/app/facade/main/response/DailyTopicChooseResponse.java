package com.tht.api.app.facade.main.response;

public record DailyTopicChooseResponse(
        boolean isChoose
) {

    public static DailyTopicChooseResponse of(final boolean result) {
        return new DailyTopicChooseResponse(result);
    }
}