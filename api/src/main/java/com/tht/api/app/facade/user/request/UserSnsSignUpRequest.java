package com.tht.api.app.facade.user.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tht.api.app.entity.enums.SNSType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserSnsSignUpRequest(

    @NotBlank(message = "phoneNumber 는 비어있을 수 없습니다.") String phoneNumber,
    @NotBlank(message = "deviceKey 는 비어있을 수 없습니다.") String deviceKey,
    @NotNull(message = "smsType 을 적어주세요") SNSType snsType,
    @NotNull(message = "sns unique id 가 필수값입니다.") String snsUniqueId,
    @NotBlank(message = "sns email 은 필수값입니다.") String email

) {

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public UserSnsSignUpRequest(
        @JsonProperty("phoneNumber") final String phoneNumber,
        @JsonProperty("deviceKey") final String deviceKey,
        @JsonProperty("snsType") final String snsType,
        @JsonProperty("snsUniqueId") final String snsUniqueId,
        @JsonProperty("email") final String email) {

        this(phoneNumber,
            deviceKey,
            SNSType.toSNSConverter(snsType),
            snsUniqueId,
            email
        );
    }
}
