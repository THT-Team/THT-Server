package com.tht.api.app.facade.main.response;

import static org.assertj.core.api.Assertions.assertThat;

import com.tht.api.app.facade.main.response.DailyFallingResponse;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DailyFallingResponseTest {

    @Test
    @DisplayName("empty() 정적 함수 생성시 객체 빈값 반환")
    void emptyValue() {

        DailyFallingResponse empty = DailyFallingResponse.empty();

        assertThat(empty.expirationUnixTime()).isEqualTo(-1);
        assertThat(empty.fallingTopicList()).isEmpty();

    }

    @Test
    @DisplayName("유닉스 타임스템프 변환 생성 테스트")
    void convertUnix() {
        LocalDateTime localDateTime = LocalDateTime.of(2023, 6, 26, 11, 0, 0);

        DailyFallingResponse response = DailyFallingResponse.of(localDateTime, List.of());

        assertThat(response.expirationUnixTime()).isEqualTo(1687744800);
    }
}