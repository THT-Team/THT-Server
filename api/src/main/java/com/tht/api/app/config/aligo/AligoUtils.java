package com.tht.api.app.config.aligo;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.tht.api.app.config.MultiValueMapConverter;
import com.tht.api.app.config.WebClientConfig;
import com.tht.api.app.config.aligo.request.SmsAuthNumberRequest;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;

import java.time.Duration;

public class AligoUtils {

    private AligoUtils() {
        throw new IllegalStateException("Utility class");
    }

    private static final JSONParser JSON_PARSER = new JSONParser();

    public static void sendAuthNumber(final String phoneNumber, final String authNumber) {
        final String response = WebClientConfig.getBaseUrl(AligoConst.SMS_SEND_URL)
            .post()
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .body(BodyInserters.fromFormData(
                MultiValueMapConverter.convert(new ObjectMapper(),
                    SmsAuthNumberRequest.of(phoneNumber, authNumber))))
            .retrieve()
            .bodyToMono(String.class)
            .timeout(Duration.ofMillis(5000))
            .blockOptional().orElseThrow(
                () -> new NullPointerException("sms 알리고 문자 전송 오류")
            );

        JSONObject jsonObject;

        try {
            jsonObject = (JSONObject) JSON_PARSER.parse(response);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        validateResultCode(
            (String) jsonObject.get("result_code"),
            (String) jsonObject.get("message")
        );
    }

    //fixme Exception refactoring
    private static void validateResultCode(final String resultCode, final String message) {
        if (!AligoConst.SMS_SENDiNG_SUCCESS_CODE.equals(resultCode)) {
            throw new NullPointerException("알리고 문자 전송 에러\n" + message);
        }
    }
}
