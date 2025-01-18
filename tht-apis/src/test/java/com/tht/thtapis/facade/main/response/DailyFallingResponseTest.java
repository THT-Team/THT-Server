package com.tht.thtapis.facade.main.response;

import com.tht.enums.dailyfalling.DailyFallingType;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class DailyFallingResponseTest {

    @Test
    @DisplayName("empty() 정적 함수 생성시 객체 빈값 반환")
    void emptyValue() {

        DailyFallingResponse empty = DailyFallingResponse.empty();

        assertThat(empty.expirationUnixTime()).isEqualTo(-1);
        assertThat(empty.fallingTopicList()).isEmpty();

    }

    @Test
    @Disabled
    @DisplayName("유닉스 타임스템프 변환 생성 테스트")
    void convertUnix() {
        LocalDateTime localDateTime = LocalDateTime.of(2023, 6, 26, 11, 0, 0);

        DailyFallingResponse response = DailyFallingResponse.of(localDateTime, DailyFallingType.ONE_CHOICE, "", List.of());

        assertThat(response.expirationUnixTime()).isEqualTo(1687744800);
    }
}