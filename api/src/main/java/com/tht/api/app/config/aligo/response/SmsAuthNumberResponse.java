package com.tht.api.app.config.aligo.response;

public record SmsAuthNumberResponse(Integer result_code, String message, Integer msg_id, Integer success_cnt,
                                    Integer error_cnt, String msg_type) {

}
