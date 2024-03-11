package com.tht.api.app.entity.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.tht.api.app.entity.enums.Gender;
import com.tht.api.app.entity.enums.UserRole;
import com.tht.api.app.entity.user.User;
import com.tht.api.exception.custom.UserCustomException;
import java.time.LocalDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class UserTest {

    private final static Gender preferGender = Gender.MALE;
    private final static Gender gender = Gender.FEMALE;
    private final static String introduction = "자기소개";
    private final static String email = "email@email.com";
    private final static String phoneNumber = "01012345678";
    private final static LocalDate birthDay = LocalDate.now();
    private final static String username = "username";

    @Test
    @DisplayName("새 유저 생성 메소드")
    void createNewUserMethod() {
        User newUser = User.createNewUser(username, birthDay, phoneNumber, email,
            introduction, gender, preferGender);

        assertThat(newUser.getUserUuid()).isNotNull();
        assertThat(newUser.getEmail()).isEqualTo(email);
        assertThat(newUser.getUsername()).isEqualTo(username);
        assertThat(newUser.getBirthDay()).isEqualTo(birthDay);
        assertThat(newUser.getPhoneNumber()).isEqualTo(phoneNumber);
        assertThat(newUser.getIntroduction()).isEqualTo(introduction);
        assertThat(newUser.getGender()).isEqualTo(gender);
        assertThat(newUser.getPreferGender()).isEqualTo(preferGender);
        assertThat(newUser.getUserRole()).isEqualTo(UserRole.NORMAL);
    }

    @Test
    @DisplayName("유저 번호 수정")
    void updatePhoneNumber() {
        User newUser = User.createNewUser(username, birthDay, phoneNumber, email,
            introduction, gender, preferGender);

        String updateNumber = "123456780";

        newUser.updatePhoneNumber(updateNumber);

        assertThat(newUser.getPhoneNumber()).isEqualTo(updateNumber);
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "03130111", "010-031-322"})
    @DisplayName("유저 번호는 숫자로 구성된 9~11자리여야 한다.")
    void validPhoneNumber(String number) {
        User newUser = User.createNewUser(username, birthDay, phoneNumber, email,
            introduction, gender, preferGender);

        assertThatThrownBy(() -> newUser.updatePhoneNumber(number))
            .isInstanceOf(UserCustomException.class)
            .hasMessageMatching("핸드폰 번호는 숫자로 구성된 9~11자리여야 합니다.");
    }

    @Test
    @DisplayName("유저 이메일 수정하기")
    void updateEmail() {

        User newUser = User.createNewUser(username, birthDay, phoneNumber, email,
            introduction, gender, preferGender);

        String email = "123hhihi@hihi.co.kr";

        newUser.updateEmail(email);
        assertThat(newUser.getEmail()).isEqualTo(email);

    }

    @ParameterizedTest
    @ValueSource(strings = {"", "03130111", "@email.com", "e@eeee,e", "empty"})
    @DisplayName("유저 이메일 양식 유효성 검사")
    void validEmail(String updateEmail) {
        User newUser = User.createNewUser(username, birthDay, phoneNumber, email,
            introduction, gender, preferGender);

        assertThatThrownBy(() -> newUser.updateEmail(updateEmail))
            .isInstanceOf(UserCustomException.class)
            .hasMessageMatching("이메일 양식이 맞지 않습니다.");
    }

}