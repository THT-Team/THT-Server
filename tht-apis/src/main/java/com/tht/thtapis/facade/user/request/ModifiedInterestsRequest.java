package com.tht.thtapis.facade.user.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;

public record ModifiedInterestsRequest(
    @Size(max = 3, message = "관심사는 최대 3개를 골라주세요")
    @NotNull(message = "interestList 는 null 이어서는 안됩니다.")
    List<Integer> interestList
) { }
