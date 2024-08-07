package com.tht.thtcommonutils.utils;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UnixTimeUtilsTest {

    @Test
    @DisplayName("유닉스 타임 변환 테스트")
    void convertUnixTimeStamp() {
        LocalDateTime localDateTime = LocalDateTime.of(2023, 6, 26, 11,0,0);

        Long unixTime = UnixTimeUtils.convertUnixTimeForUTC(localDateTime);

        assertThat(unixTime).isEqualTo(1687744800);
    }
}