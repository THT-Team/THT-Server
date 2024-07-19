package com.tht.infra.user.enums;

import com.tht.enums.user.Gender;
import com.tht.enums.user.converter.GenderConverter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class GenderConverterTest {

    @ParameterizedTest
    @MethodSource
    @DisplayName("성별 컨버터 테스트  - string to enum)")
    void convertToEntityAttributeTest(final Gender gender, String column) {
        GenderConverter converter = new GenderConverter();

        assertThat(converter.convertToEntityAttribute(column)).isEqualTo(gender);
    }

    private static Stream<Arguments> convertToEntityAttributeTest() {
        return Stream.of(
            Arguments.of(Gender.MALE, "MALE"),
            Arguments.of(Gender.FEMALE, "FEMALE"),
            Arguments.of(Gender.BISEXUAL, "BISEXUAL")
        );
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("성별 컨버터 테스트  - enum to string)")
    void convertToDatabaseColumnTest(final Gender gender, String column) {
        GenderConverter converter = new GenderConverter();

        assertThat(converter.convertToDatabaseColumn(gender)).isEqualTo(column);
    }

    private static Stream<Arguments> convertToDatabaseColumnTest() {
        return Stream.of(
            Arguments.of(Gender.MALE, "MALE"),
            Arguments.of(Gender.FEMALE, "FEMALE"),
            Arguments.of(Gender.BISEXUAL, "BISEXUAL")
        );
    }

}