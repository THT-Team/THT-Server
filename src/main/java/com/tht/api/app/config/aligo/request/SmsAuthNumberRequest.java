package com.tht.api.app.config.aligo.request;

import com.tht.api.app.config.aligo.AligoConst;

public record SmsAuthNumberRequest(String key, String user_id, String sender, String receiver, String msg) {

    public static SmsAuthNumberRequest of(final String receiver, final String authNumber) {
        final String msg = "회원가입 인증번호 : [ " + authNumber + " ]";

        return new SmsAuthNumberRequest(AligoConst.API_KEY, AligoConst.USER_ID, AligoConst.SENDER,
            receiver, msg);
    }
}
