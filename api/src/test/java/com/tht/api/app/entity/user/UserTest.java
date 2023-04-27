package com.tht.api.app.entity.user;

import static org.assertj.core.api.Assertions.assertThat;

import com.tht.api.app.entity.enums.Gender;
import com.tht.api.app.entity.enums.UserRole;
import java.time.LocalDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
}