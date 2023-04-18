package com.tht.api.app.entity.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.tht.api.exception.custom.EnumStateNotFoundException;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class GenderTest {

    @ParameterizedTest
    @MethodSource
    @DisplayName("성별 컨버터 테스트 (성공)")
    void converterTest(final Gender gender, final String column) {

        assertThat(Gender.toConverter(column)).isEqualTo(gender);
    }

    private static Stream<Arguments> converterTest() {
        return Stream.of(
            Arguments.of(Gender.MALE, "MALE"),
            Arguments.of(Gender.FEMALE, "FEMALE"),
            Arguments.of(Gender.BISEXUAL, "BISEXUAL")
        );
    }

    @Test
    @DisplayName("성별 컨버터 테스트 (실패)")
    void converterTest_fail() {

        assertThatThrownBy(() -> Gender.toConverter("아무거나"))
            .isInstanceOf(EnumStateNotFoundException.class)
            .hasMessageMatching("성별에 아무거나가 존재하지 않습니다.");
    }
}