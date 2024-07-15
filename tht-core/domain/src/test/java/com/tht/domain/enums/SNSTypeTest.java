package com.tht.domain.enums;

import com.tht.infra.exception.EnumStateNotFoundException;
import com.tht.infra.user.enums.SNSType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SNSTypeTest {

    @Test
    @DisplayName("normal 아닌 sns 타입이면 true")
    void isSNSTest() {
        Assertions.assertThat(SNSType.GOOGLE.isSns()).isTrue();
    }

    @Test
    @DisplayName("normal 타입이면 false")
    void isNormalTest() {
        assertThat(SNSType.NORMAL.isSns()).isFalse();
    }

    @ParameterizedTest
    @ValueSource(strings = {"NAVER", "GOOGLE", "KAKAO", "NORMAL"})
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

    @ParameterizedTest
    @EnumSource(SNSType.class)
    @DisplayName("SNS Type 만 반환 - Normal 타입 필터링(성공)")
    void toSnsConvert_fail(final SNSType snsType) {

        if (snsType.equals(SNSType.NORMAL)) {
            assertThatThrownBy(() -> SNSType.toSNSConverter("NORMAL"))
                .isInstanceOf(EnumStateNotFoundException.class)
                .hasMessageContaining("SNS 통합회원가입은 NORMAL 을 제외한 유효한 타입만 가능합니다.");
            return;
        }

        assertThat(SNSType.toSNSConverter(snsType.name())).isEqualTo(snsType);
    }
}