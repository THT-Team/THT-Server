package com.tht.domain.entity.config.aligo;

import org.springframework.context.annotation.Configuration;

@Configuration
public abstract class AligoConst {
    public static final String API_KEY = "ckuo7x3g4vzvcady6efjac4rmrn5pqro";
    public static final String USER_ID = "tht221202";
    public static final String SENDER = "01025948602";

    public static final String SMS_SEND_URL = "https://apis.aligo.in/send/";
    public static final Long SMS_SENDiNG_SUCCESS_CODE = 1L;

    private AligoConst(){
        throw new NullPointerException("AligoConst class is const abstract class");
    }
}
