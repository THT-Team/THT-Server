package com.tht.api.app.fixture.user;

import static org.assertj.core.api.Assertions.assertThat;

import com.tht.api.app.entity.enums.SNSType;
import com.tht.api.app.facade.user.request.UserSignUpInfoResponse;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserSignUpInfoResponseFixtureTest {

    @Test
    @DisplayName("유저 회원가입 이력, 가입이력이 존재하면 Normal 타입 항상 포함")
    void containNormalType() {
        UserSignUpInfoResponse response = UserSignUpInfoResponse.of(true, List.of());

        String normal = SNSType.NORMAL.name();

        assertThat(response.typeList()).contains(normal);
    }

    @Test
    @DisplayName("유저 회원가입 이력, 가입이력이 존재하면 Normal 타입 항상 포함(다른 타입이 존재할 떄)")
    void containSNSType() {
        UserSignUpInfoResponse response = UserSignUpInfoResponse.of(true, List.of(SNSType.GOOGLE.name()));

        String normal = SNSType.NORMAL.name();
        String google = SNSType.GOOGLE.name();

        assertThat(response.typeList()).contains(normal,google);
    }
}