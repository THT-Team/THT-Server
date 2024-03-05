package com.tht.api.app.facade.user.request;

import com.tht.api.app.entity.user.UserLocationInfo;
import jakarta.validation.constraints.NotNull;

public record UserLocationRequest(
    @NotNull(message = "주소는 필수값입니다.")
    String address,
    @NotNull(message = "자치구 코드는 필수값 입니다.")
    Integer regionCode,
    float lat,
    float lon
) {

    public UserLocationInfo toEntity(final String userUuid) {
        return UserLocationInfo.create(userUuid, address, regionCode, lat, lon);
    }
}
