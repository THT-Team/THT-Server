package com.tht.api.app.config.aligo;


import com.tht.api.app.config.WebClientConfig;
import com.tht.api.app.config.aligo.request.SmsAuthNumberRequest;
import com.tht.api.app.config.aligo.response.SmsAuthNumberResponse;
import reactor.core.publisher.Mono;

import java.time.Duration;

public class AligoUtils {

    public static void sendAuthNumber(final String phoneNumber, final String authNumber) {
        final SmsAuthNumberResponse response = WebClientConfig.getBaseUrl(AligoConst.SMS_SEND_URL)
                .post()
                .body(Mono.just(SmsAuthNumberRequest.of(phoneNumber, authNumber)), SmsAuthNumberRequest.class)
                .retrieve()
                .bodyToMono(SmsAuthNumberResponse.class)
                .timeout(Duration.ofMillis(5000))
                .block();

        //fixme exception refactoring

        assert response != null;
        if(!AligoConst.SMS_SENDiNG_SUCCESS_CODE.equals(response.result_code())){
            throw new NullPointerException("알리고 문자 전송 에러\n" + response.message());
        }
    }
}
