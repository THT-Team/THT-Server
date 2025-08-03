package com.tht.thtcommonutils.utils;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ConvertAgeUtilsTest {

    @Test
    @DisplayName("나이 계산 테스트")
    void getAge() {
        LocalDate birthDate = LocalDate.of(1990, 5, 15);
        int age = ConvertAgeUtils.covertBeforeBirthAge(birthDate);

        int expectedAge = LocalDate.now().getYear() - 1990;
        if (LocalDate.now().getDayOfYear() < birthDate.getDayOfYear()) {
            expectedAge--;
        }

        assertThat(age).isEqualTo(expectedAge);
    }

    @Test
    @DisplayName("생일이 지난 경우 나이 계산")
    void getAge_afterBirthday() {
        LocalDate pastBirthday = LocalDate.now().minusYears(25).minusDays(1);
        int age = ConvertAgeUtils.covertBeforeBirthAge(pastBirthday);

        assertThat(age).isEqualTo(25);
    }

    @Test
    @DisplayName("생일이 아직 안 지난 경우 나이 계산")
    void getAge_beforeBirthday() {
        LocalDate futureBirthday = LocalDate.now().minusYears(25).plusDays(1);

        int age = ConvertAgeUtils.covertBeforeBirthAge(futureBirthday);

        assertThat(age).isEqualTo(24);
    }
}