package com.tht.api.app.facade.user.request;

public record UserAlarmAgreementModifyRequest(

    boolean marketingAlarm,
    boolean newMatchSuccessAlarm,
    boolean likeMeAlarm,
    boolean newConversationAlarm,
    boolean talkAlarm
) {

}
