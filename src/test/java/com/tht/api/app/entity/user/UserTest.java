package com.tht.api.app.entity.user;

import com.tht.api.app.fixture.user.UserFixture;
import com.tht.api.exception.custom.UserCustomException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class UserTest {

    @Test
    @DisplayName("새 유저 생성 메소드")
    void createNewUserMethod() {
        User newUser = UserFixture.make();

        assertThat(newUser.getUserUuid()).isNotNull();
    }

    @Test
    @DisplayName("유저 번호 수정")
    void updatePhoneNumber() {
        User newUser = UserFixture.make();

        String updateNumber = "123456780";

        newUser.updatePhoneNumber(updateNumber);

        assertThat(newUser.getPhoneNumber()).isEqualTo(updateNumber);
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "03130111", "010-031-322"})
    @DisplayName("유저 번호는 숫자로 구성된 9~11자리여야 한다.")
    void validPhoneNumber(String number) {
        User newUser = UserFixture.make();

        assertThatThrownBy(() -> newUser.updatePhoneNumber(number))
            .isInstanceOf(UserCustomException.class)
            .hasMessageMatching("핸드폰 번호는 숫자로 구성된 9~11자리여야 합니다.");
    }

    @Test
    @DisplayName("유저 이메일 수정하기")
    void updateEmail() {

        User newUser = UserFixture.make();

        String email = "123hhihi@hihi.co.kr";

        newUser.updateEmail(email);
        assertThat(newUser.getEmail()).isEqualTo(email);

    }

    @ParameterizedTest
    @ValueSource(strings = {"", "03130111", "@email.com", "e@eeee,e", "empty"})
    @DisplayName("유저 이메일 양식 유효성 검사")
    void validEmail(String updateEmail) {
        User newUser = UserFixture.make();

        assertThatThrownBy(() -> newUser.updateEmail(updateEmail))
            .isInstanceOf(UserCustomException.class)
            .hasMessageMatching("이메일 양식이 맞지 않습니다.");
    }

}