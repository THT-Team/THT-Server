package com.tht.domain.entity.config.aligo;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.tht.domain.entity.config.MultiValueMapConverter;
import com.tht.domain.entity.config.WebClientConfig;
import com.tht.domain.entity.config.aligo.request.SmsAuthNumberRequest;
import com.tht.domain.exception.AligoException;
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
    private static final ObjectMapper objectMapper = new ObjectMapper();

    //todo. test
    public static void sendAuthNumber(final String phoneNumber, final String authNumber) {
        final String response = WebClientConfig.getBaseUrl(AligoConst.SMS_SEND_URL)
            .post()
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .body(BodyInserters.fromFormData(
                MultiValueMapConverter.convert(objectMapper,
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
            jsonObject.get("result_code"),
            (String) jsonObject.get("message")
        );
    }

    private static void validateResultCode(final Object resultCode, final String message) {
        if (resultCode.getClass().equals(String.class)){
            return;
        }

        resultCodeTypeToLong((Long) resultCode, message);
    }

    private static void resultCodeTypeToLong(final Long resultCode, final String message) {
        if ((!AligoConst.SMS_SENDiNG_SUCCESS_CODE.equals(resultCode))) {
            throw AligoException.of(message);
        }
    }
}
