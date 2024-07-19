package com.tht.thtapis.facade.user.response;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import com.tht.enums.user.SNSType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserSignUpInfoResponseTest {

    @Test
    @DisplayName("유저 회원가입 이력이 없다면 빈리스트 반환")
    void containNormalType() {
        UserSignUpInfoResponse response = UserSignUpInfoResponse.ofEnum(List.of());

        assertThat(response.isSignUp()).isFalse();
        assertThat(response.typeList()).isEmpty();
    }

    @Test
    @DisplayName("유저 회원가입 이력, 가입이력이 존재하면 Normal 타입 항상 포함(다른 타입이 존재할 떄)")
    void containSNSType() {
        UserSignUpInfoResponse response = UserSignUpInfoResponse.ofEnum(List.of(SNSType.GOOGLE));

        String normal = SNSType.NORMAL.name();
        String google = SNSType.GOOGLE.name();

        assertThat(response.typeList()).contains(normal, google);
    }

    @Test
    @DisplayName("Normal 타입으로만 회원가입 일 경우 Normal 한번만 나옴")
    void normalSignUp() {

        UserSignUpInfoResponse response = UserSignUpInfoResponse.ofEnum(List.of(SNSType.NORMAL));

        String normal = SNSType.NORMAL.name();

        assertThat(response.typeList()).containsOnlyOnce(normal);

    }
}