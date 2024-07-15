package com.tht.thtapis.facade.main.response;

public record DailyTopicChooseResponse(
        boolean isChoose
) {

    public static DailyTopicChooseResponse isChooseDone() {
        return new DailyTopicChooseResponse(true);
    }

    public static DailyTopicChooseResponse isNotChoose() {
        return new DailyTopicChooseResponse(false);
    }

}