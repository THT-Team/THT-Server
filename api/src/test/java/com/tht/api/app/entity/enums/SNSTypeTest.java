package com.tht.api.app.entity.enums;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.tht.api.exception.custom.EnumStateNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class SNSTypeTest {

    @Test
    @DisplayName("normal 아닌 sns 타입이면 true")
    void isSNSTest() {
        assertThat(SNSType.GOOGLE.isSns()).isTrue();
    }

    @Test
    @DisplayName("normal 타입이면 false")
    void isNormalTest() {
        assertThat(SNSType.NORMAL.isSns()).isFalse();
    }

    @ParameterizedTest
    @ValueSource(strings = {"NAVER","GOOGLE", "KAKAO", "NORMAL"})
    @DisplayName("String to Enum Convert (성공)")
    void toConverter_success(final String name) {

        SNSType snsType = SNSType.valueOf(name);
        assertThat(SNSType.toConverter(name)).isEqualTo(snsType);
    }

    @Test
    @DisplayName("String to Enum Convert (실패)")
    void toConverter_fail() {

        String name = "없는 snstype";
        assertThatThrownBy(() -> SNSType.toConverter(name))
            .isInstanceOf(EnumStateNotFoundException.class)
            .hasMessageContaining(String.format("%s 는 유효하지 않은 SNS 타입입니다.", name));
    }
}