package com.tht.thtapis.facade.user.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tht.enums.user.SNSType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record UserSNSLoginRequest(

    @NotEmpty(message = "email 를 입력해주세요.") String email,
    @NotNull(message = "snsType 를 입력해주세요.") SNSType snsType,
    @NotEmpty(message = "snsUniqueId 를 입력해주세요.") String snsUniqueId,
    @NotEmpty(message = "deviceKey 를 입력해주세요.") String deviceKey

) {

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public UserSNSLoginRequest(
        @JsonProperty("email") final String email,
        @JsonProperty("snsType") final String snsType,
        @JsonProperty("snsUniqueId") final String snsUniqueId,
        @JsonProperty("deviceKey") final String deviceKey) {

        this(email,
            SNSType.toSNSConverter(snsType),
            snsUniqueId,
            deviceKey
        );
    }
}
