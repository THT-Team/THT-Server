package com.tht.thtapis.facade.user.request;

import java.util.Objects;

public record MainScreenUserInfoRequest(

    Long userDailyFallingCourserIdx,
    Integer size
) {

    public int getSize() {

        int defaultSize = 100;
        return Objects.isNull(size) ? defaultSize : size();
    }
}
