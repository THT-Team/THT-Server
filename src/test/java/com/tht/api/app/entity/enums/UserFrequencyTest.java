package com.tht.api.app.entity.enums;

import com.tht.api.exception.custom.EnumStateNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class UserFrequencyTest {

    private static final String NONE_VALID_FIND_MESSAGE_FORMAT = "는 유효하지 않은 User Frequency 타입입니다.";

    @ParameterizedTest
    @ValueSource(strings = {"", " ", " NONE ", "HEAVY"})
    @DisplayName("User Frequency 잘못된 파라미터 exception 처리 테스트")
    public void unValidParameterToConverter(String name) {

        assertThatThrownBy(() -> UserFrequency.toConverter(name))
                .isInstanceOf(EnumStateNotFoundException.class)
                .hasMessageMatching(name + NONE_VALID_FIND_MESSAGE_FORMAT);
    }

    @ParameterizedTest
    @NullSource
    @DisplayName("User Frequency 잘못된 파라미터 exception 처리 테스트(NULL)")
    public void unValidParameterToConvertersWithNull(String name) {

        assertThatThrownBy(() -> UserFrequency.toConverter(name))
                .isInstanceOf(EnumStateNotFoundException.class)
                .hasMessageMatching(name + NONE_VALID_FIND_MESSAGE_FORMAT);
    }
}