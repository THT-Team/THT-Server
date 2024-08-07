package com.tht.enums.user;

import com.tht.enums.EnumStateNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class UserFrequencyTest {

    private static final String NONE_VALID_FIND_MESSAGE_FORMAT = "는 유효하지 않은 User Frequency 타입입니다. (ex)";

    @ParameterizedTest
    @ValueSource(strings = {"", " ", " NONE ", "HEAVY"})
    @DisplayName("User Frequency 잘못된 파라미터 exception 처리 테스트")
    void unValidParameterToConverter(String name) {

        assertThatThrownBy(() -> UserFrequency.toConverter(name))
                .isInstanceOf(EnumStateNotFoundException.class)
                .hasMessageStartingWith(name + NONE_VALID_FIND_MESSAGE_FORMAT);
    }

    @ParameterizedTest
    @NullSource
    @DisplayName("User Frequency 잘못된 파라미터 exception 처리 테스트(NULL)")
    void unValidParameterToConvertersWithNull(String name) {

        assertThatThrownBy(() -> UserFrequency.toConverter(name))
                .isInstanceOf(EnumStateNotFoundException.class)
                .hasMessageStartingWith(name + NONE_VALID_FIND_MESSAGE_FORMAT);
    }
}