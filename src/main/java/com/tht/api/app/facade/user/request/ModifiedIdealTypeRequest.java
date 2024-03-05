package com.tht.api.app.facade.user.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;

public record ModifiedIdealTypeRequest(
    @Size(max = 3, message = "이상형타입은 최대 3개를 골라주세요")
    @NotNull(message = "idealTypeList 는 null 이어서는 안됩니다.")
    List<Integer> idealTypeList
) {}
