package com.tht.thtcommonutils.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CustomDateFormatUtilsTest {


    @Test
    @DisplayName("CustomDateFormatter yyyy-MM-dd HH:mm:ss 포매터 테스트")
    void getDateTimeInstance() {
        String day = "1997-11-11 23:11:03";
        LocalDateTime date = LocalDateTime.of(1997, 11, 11,23,11,3);

        Assertions.assertThat(LocalDateTime.parse(day, CustomDateFormatUtils.getDateTimeInstance()))
            .isEqualTo(date);
    }

    @Test
    @DisplayName("CustomDateFormatter yyyy.MM.dd 포매터 테스트")
    void getDotDateInstance() {
        String day = "1997.11.11";
        LocalDate date = LocalDate.of(1997, 11, 11);

        Assertions.assertThat(LocalDate.parse(day, CustomDateFormatUtils.getDotDateInstance()))
            .isEqualTo(date);
    }

    @Test
    @DisplayName("CustomDateFormatter yyyyMMdd 포매터 테스트")
    void getNoHyphenDateInstance() {
        String day = "19971111";
        LocalDate date = LocalDate.of(1997, 11, 11);

        Assertions.assertThat(LocalDate.parse(day, CustomDateFormatUtils.getNoHyphenDateInstance()))
            .isEqualTo(date);
    }
}