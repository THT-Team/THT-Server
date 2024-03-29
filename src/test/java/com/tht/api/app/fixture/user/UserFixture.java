package com.tht.api.app.fixture.user;

import com.tht.api.app.entity.enums.Gender;
import com.tht.api.app.entity.enums.UserFrequency;
import com.tht.api.app.entity.enums.UserReligion;
import com.tht.api.app.entity.enums.UserRole;
import com.tht.api.app.entity.user.User;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserFixture {

    private final static String username = "username";
    private final static LocalDate birthDay = LocalDate.now();
    private final static String phoneNumber = "01012341513";
    private final static String email = "email@emila";
    private final static String introduction = "소개소개";
    private final static Gender gender = Gender.FEMALE;
    private final static Gender preferGender = Gender.BISEXUAL;
    private final static int tall = 170;
    private final static UserFrequency smoking = UserFrequency.SOMETIMES;
    private final static UserFrequency drinking = UserFrequency.SOMETIMES;
    private final static UserReligion religion = UserReligion.CATHOLICISM;

    public static User make() {
        return User.createNewUser(username, birthDay, phoneNumber, email, introduction, gender,
            preferGender, tall, smoking, drinking, religion
        );
    }

    public static User make(final String userUuid, final String userRole) {

        return new User(1L, userUuid, username, birthDay, phoneNumber, email, introduction, gender,
            preferGender, UserRole.valueOf(userRole), tall, smoking, drinking, religion);

    }
}
